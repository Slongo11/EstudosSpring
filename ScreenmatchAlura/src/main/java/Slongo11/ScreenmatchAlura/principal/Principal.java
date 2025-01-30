package Slongo11.ScreenmatchAlura.principal;

import Slongo11.ScreenmatchAlura.model.*;
import Slongo11.ScreenmatchAlura.repository.SerieRepository;
import Slongo11.ScreenmatchAlura.service.ConsumoAPI;
import Slongo11.ScreenmatchAlura.service.ConverterDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
	private Scanner scanner = new Scanner(System.in);
	private ConsumoAPI consumoAPI = new ConsumoAPI();
	private ConverterDados conversor = new ConverterDados();
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String API_KEY ="&apikey=6585022c";
	private List<DadosSerie> dadosSerie = new ArrayList<>();
	private SerieRepository repository;
	private List<Serie> serie;
	public Principal(SerieRepository repository) {
		this.repository = repository;
	}

	public void exibeMenu() {
		var opcao = -1;
		while (opcao != 0){
		var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar Series buscadas
                4 - Buscar Serie Por Nome
                5 - Buscar Serie Por Ator
                6 - Mostra Top 5
                7 - Buscar Serie por categoria
                8 - Busca Serie por Numero de Temporada e Avaliacão
                9 - Busca Episodio por Trecho
                10 - Busca Top 5 episodio por Serie
                11 - Buscar Episodios Depois de uma Data
                0 - Sair
                """;

		System.out.println(menu);
		opcao = scanner.nextInt();
		scanner.nextLine();

		switch (opcao) {
			case 1: buscarSerieWeb();
				break;
			case 2: buscarEpisodioPorSerie();
				break;
			case 3: listarSeriesBuscadas();
				break;
			case 4: buscaSeriePorNome();
				break;
			case 5: buscaSeriePorAtor();
				break;
			case 6: mostraTop5Series();
				break;
			case 7: buscaSeriePorCategoria();
				break;
			case 8: buscaSeriePorTemporaAvalia();
				break;
			case 9: buscarEpisodioPorTrecho();
				break;
			case 10: topEpisodioPorSerie();
				break;
			case 11: buscarEpisodioAposData();
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida");
		}
		}
	}

	private void buscarSerieWeb() {
		DadosSerie dados = getDadosSerie();
		Serie serie = new Serie(dados);
		//dadosSerie.add(dados);
		repository.save(serie);
		System.out.println(dados);
	}

	private DadosSerie getDadosSerie() {
		System.out.println("Digite o nome da série para busca");
		var nomeSerie = scanner.nextLine();
		var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		return dados;
	}

	private void buscarEpisodioPorSerie(){
		listarSeriesBuscadas();
		System.out.println("Informe o nome da série para busca");
		var nomeSerie = scanner.nextLine();
		Optional<Serie> serieEscolhida = repository.findByTituloContainingIgnoreCase(nomeSerie);
		if (serieEscolhida.isPresent()) {

			Serie serieEnco = serieEscolhida.get();
			List<DadosTemporada> temporadas = new ArrayList<>();

			for (int i = 1; i <= serieEnco.getTotalTemporadas(); i++) {
				var json = consumoAPI.obterDados(ENDERECO + serieEnco.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
				DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
				temporadas.add(dadosTemporada);
			}
			temporadas.forEach(System.out::println);
			List<Episodios> episodiosEncontrados = temporadas.stream()
					.flatMap(t -> t.episodios().stream()
							.map(e -> new Episodios(t.numero(),e)))
					.collect(Collectors.toList());
			serieEnco.setEpisodios(episodiosEncontrados);
			repository.save(serieEnco);
		}else{
			System.out.println("Série não encontrada");
		}
	}

	private void listarSeriesBuscadas(){
		serie = repository.findAll();
		serie.stream()
				.sorted(Comparator.comparing(Serie::getTitulo))
				.forEach(System.out::println);
	}

	private Optional<Serie> buscaSeriePorNome(){
		System.out.println("Digite o nome da série para busca");
		var nomeSerie = scanner.nextLine();
		Optional<Serie> serieBuscada = repository.findByTituloContainingIgnoreCase(nomeSerie);
		if (serieBuscada.isPresent()) {
			System.out.println(serieBuscada.get());
			return serieBuscada;
		}else{
			System.out.println("Nada encontrado");
			return Optional.empty();
		}
	}

	private void buscaSeriePorAtor(){
		System.out.println("Digite o nome do Ator para buscar as Series");
		var nomeAtor = scanner.nextLine();
		List<Serie> series = repository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor,8);
		series.forEach(System.out::println);

	}

	private void mostraTop5Series(){
		List<Serie> series =  repository.findTop5ByOrderByAvaliacaoDesc();
		series.forEach(s -> System.out.println(s.getTitulo() + " - " + s.getAvaliacao()));
	}
	private void buscaSeriePorCategoria(){
		System.out.println("Digite a Categoria para buscar as Series");
		var nomeGenero = scanner.nextLine();
		Categoria categoria = Categoria.fromPortugues(nomeGenero);
		List<Serie> series = repository.findByGenero(categoria);
		System.out.println("Serie da categoria " + nomeGenero);
		series.forEach(System.out::println);


	}
	private void buscaSeriePorTemporaAvalia(){
		System.out.println("Digite o numero de Temporadas para buscar as Series");
		var numeroTemporadas = Integer.parseInt(scanner.nextLine());
		System.out.println("Digite a avaliação minima para buscar as Series");
		var nomeAvaliacao = Double.parseDouble(scanner.nextLine());
		List<Serie> series = repository.tempoaradasEAvaliacao(numeroTemporadas,nomeAvaliacao);
		series.forEach(System.out::println);


	}
	private void buscarEpisodioPorTrecho(){
		System.out.println("Digite um trecho do episodio");
		var trecho = scanner.nextLine();
		List<Episodios> episodios = repository.buscarEpisodioPorTrecho(trecho);
		episodios.forEach(e -> System.out.printf("Serie:%s ---- Temporada = %s %s,avaliacao = %s\n",e.getSerie().getTitulo(),e.getTemporada(),e.getTitulo(),e.getAvaliacao()));
	}

	private void topEpisodioPorSerie(){
		Optional<Serie> serieBuscada = buscaSeriePorNome();
		if (serieBuscada.isPresent()) {
			Serie serie = serieBuscada.get();
			List<Episodios> topEpisodio = repository.topEpisodioPorSerie(serie);
			topEpisodio.forEach(System.out::println);
		}else{
			System.out.println("Nenhuma série encontrada");
		}
	}
	private void buscarEpisodioAposData(){
		Optional<Serie> serieBuscada = buscaSeriePorNome();
		if (serieBuscada.isPresent()) {
			System.out.println("Digite ano limite de lançamento");
			var anoLimite = Integer.parseInt(scanner.nextLine());
			Serie serie = serieBuscada.get();
			List<Episodios> anoEpisodio = repository.anoEpisodioPorSerie(serie,anoLimite);
			anoEpisodio.forEach(System.out::println);
		}else{
			System.out.println("Nenhuma série encontrada");
		}
	}
}
