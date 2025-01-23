package Slongo11.ScreenmatchAlura.principal;

import Slongo11.ScreenmatchAlura.model.DadosEpisodio;
import Slongo11.ScreenmatchAlura.model.DadosSerie;
import Slongo11.ScreenmatchAlura.model.DadosTemporada;
import Slongo11.ScreenmatchAlura.model.Episodios;
import Slongo11.ScreenmatchAlura.service.ConsumoAPI;
import Slongo11.ScreenmatchAlura.service.ConverterDados;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
	private Scanner scanner = new Scanner(System.in);
	private ConsumoAPI consumoAPI = new ConsumoAPI();
	private ConverterDados conversor = new ConverterDados();
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String API_KEY ="&apikey=6585022c";
	public void exibeMenu(){
		try {
			System.out.println("Escreva o nome da série para ser buscada");
			String nome = scanner.nextLine();
			nome.toLowerCase();
			nome = nome.replace(" ", "+");
			String json = consumoAPI.obterDados(ENDERECO + nome + API_KEY);


			DadosSerie serie = conversor.obertDados(json, DadosSerie.class);

			List<DadosTemporada> temporadas = new ArrayList<>();
			for (int i = 1 ; i <= serie.totalTemporadas(); i++){
				json = consumoAPI.obterDados(ENDERECO + nome+ "&season="+ i + API_KEY);
				temporadas.add(conversor.obertDados(json, DadosTemporada.class));
			}

			temporadas.forEach(t -> t.episodios()
					.forEach(e -> System.out.println(e.titulo())));

			List<DadosEpisodio> episodios = temporadas.stream()
					.flatMap(t -> t.episodios().stream())
					.toList();
			// top 5 episódios
			episodios.stream()
					.filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
					.sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
					.limit(5)
					.forEach(System.out::println);

			List<Episodios> lista = temporadas.stream()
					.flatMap(t -> t.episodios().stream()
							.map(e -> new Episodios(t.numero(), e))
					).collect(Collectors.toList());
			lista.forEach(System.out::println);

//			System.out.println("Informe o ano que desja que seja mostrado a série");
//			Integer ano = scanner.nextInt();
//			scanner.nextLine();
//
//			LocalDate localDate = LocalDate.of(ano, 1, 1);
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//
//			lista.stream()
//					.filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(localDate))
//					.forEach(e -> System.out.println(e.toString() + "\n" + e.getDataLancamento().format(formatter)));
//
//			System.out.println("Escreva um nome de um episodio");
//			String nomeEpisodio = scanner.nextLine();
//			Optional<Episodios> episodioEncontrado = lista.stream()
//					.filter(e -> e.getTitulo().toUpperCase().contains(nomeEpisodio.toUpperCase()))
//					.findFirst();
//
//
//			if (episodioEncontrado.isPresent()) {
//				System.out.println("Encontrado!!");
//				System.out.println("Titulo: " + episodioEncontrado.get().getTitulo() + " Temporada: " + episodioEncontrado.get().getTemporada());
//			}

			Map<Integer, Double> avaliacaoDaTemporada = lista.stream()
					.filter(e -> e.getAvaliacao() > 0.0)
					.collect(Collectors.groupingBy(Episodios::getTemporada,
							Collectors.averagingDouble(Episodios::getAvaliacao)));
			System.out.println(avaliacaoDaTemporada);
			DoubleSummaryStatistics est = lista.stream()
					.filter(e -> e.getAvaliacao() > 0.0)
					.collect(Collectors.summarizingDouble(Episodios::getAvaliacao));
			System.out.println(est);
		}catch (Exception e){
			e.printStackTrace();
		}



	}
}
