package Slongo11.ColocandoEmPratica.principal;

import Slongo11.ColocandoEmPratica.Service.ConsumoAPI;
import Slongo11.ColocandoEmPratica.Service.LeituraDados;
import Slongo11.ColocandoEmPratica.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrincipalApp {
	Scanner scanner;
	private final String CARRO ="carros/";
	private final String MOTOS ="motos/";
	private final String CAMINHAO ="caminhoes/";
	private final String MARCA ="marcas/";
	private final String URL_INICIO = "https://parallelum.com.br/fipe/api/v1/";
	private final String MODELO = "modelos/";
	private final String ANOS = "anos/";
	private ConsumoAPI consumoAPI;
	LeituraDados leituraDados;

	public PrincipalApp() {
		scanner = new Scanner(System.in);
		consumoAPI = new ConsumoAPI();
		leituraDados = new LeituraDados();
	}
	public void executa() {



		boolean run = true;
		while(run){
			try {
				menuPrincipal();
				run = false;
				Integer opcao = Integer.parseInt(scanner.nextLine());
				switch(opcao) {
					case 0:System.exit(0);
						break;
					case 1:pesquisaMarcas(URL_INICIO+CARRO+MARCA);
						break;
					case 2:pesquisaMarcas(URL_INICIO+MOTOS+MARCA);
						break;
					case 3:pesquisaMarcas(URL_INICIO+CAMINHAO+MARCA);
						break;
					default:
						System.out.println("Opcao invalida");
						run = true;
				}
			}catch (NumberFormatException e) {
				System.out.println("Valor não numérico informado");
				run = true;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void menuPrincipal() {
		System.out.println("""
				==============================
				DIGITE O NUMERO DO VEÍCULO QUE 
				DESEJA SER PESQUISADO A FIPE
				[1] CARRO
				[2] MOTO
				[3] CAMINHÃO
				[0] SAIR
				""");
	}
	private void pesquisaMarcas(String endereco){
		String json = consumoAPI.obterDados(endereco);
		List<DadosMarca> dadosMarcas = leituraDados.jsonToObjectList(json, DadosMarca.class);
		dadosMarcas.forEach(m -> System.out.println(String.format("Código: %s --- Marca: %s",m.codigo(),m.nome())));


		Optional<DadosMarca> marcaEscolhida = Optional.empty();
		boolean run = true;
		while(run){
			run = false;
			System.out.println("Informe o código da marca ou o nome da marca");
			String marca = scanner.nextLine();
			String finalMarca = marca.toUpperCase();;
			marcaEscolhida = dadosMarcas.stream()
					.filter(m -> Integer.toString(m.codigo()).equals(finalMarca) || m.nome().toUpperCase().contains(finalMarca))
					.findFirst();
			if (marcaEscolhida.isEmpty()){
				run = true;
			}
		}


		pequisaModelos(endereco+marcaEscolhida.get().codigo()+"/"+MODELO);


	}
	private void pequisaModelos(String endereco){
		String json = consumoAPI.obterDados(endereco);
		Modelos modelos = leituraDados.jsonToObject(json, Modelos.class);
		List<DadosModelo> dadosModelos = modelos.dadosMarcas().stream().collect(Collectors.toList());
		dadosModelos.forEach(m -> System.out.println(String.format("Codigo: %s --- Nome Modelo: %s",m.codigo(),m.modeloNome())));



		List<DadosModelo> modeloEscolhido = null;
		Optional<DadosModelo>  dadosModelo = Optional.empty();
		boolean run = true;
		while(run){
			try {
				run = false;
				System.out.println("Informe o código da modelo ou o nome da modelo");
				String modelo = scanner.nextLine();

				modeloEscolhido = dadosModelos.stream()
						.filter(m -> Integer.toString(m.codigo()).equals(modelo) || m.modeloNome().toUpperCase().contains(modelo.toUpperCase()))
						.collect(Collectors.toList());
				if (modeloEscolhido.isEmpty()){
					System.out.println("Nenhum dado encontrado");
					run = true;
				}
				if (modeloEscolhido.size() == 1){
					run = true;
					break;
				}
				if(!run){
					modeloEscolhido.forEach(System.out::println);
					System.out.println("Informe o código da modelo ou o nome da modelo");
					String modelo2 = scanner.nextLine();

					dadosModelo = modeloEscolhido.stream()
							.filter(m -> Integer.toString(m.codigo()).equals(modelo2) || m.modeloNome().toUpperCase().contains(modelo2.toUpperCase()))
							.findFirst();
					if (dadosModelo.isPresent()){
						break;
					}else{
						run = true;
					}
				}
			}catch (NumberFormatException e) {
				System.out.println("Valor numérico incorreto");
				run = true;
			}
		}

		if(run){
			pequisaAnos(endereco+modeloEscolhido.getFirst().codigo()+"/"+ANOS);
		}
		if(!run){
			pequisaAnos(endereco+dadosModelo.get().codigo()+"/"+ANOS);
		}


	}

	private void pequisaAnos(String endereco){
		String json = consumoAPI.obterDados(endereco);
		List<DadosAnosVeiculos> dadosVeiculo = leituraDados.jsonToObjectList(json, DadosAnosVeiculos.class);
		List<String> anos = dadosVeiculo.stream()
						.map(e -> e.ano())
								.collect(Collectors.toList());


		exibeDadosVeiculos(endereco,anos);
	}
	private void exibeDadosVeiculos(String endereco, List<String> anos){

		List<Veiculo> veiculos = new ArrayList<>();
		anos.forEach(e ->
				{
					String json = consumoAPI.obterDados(endereco+e+"/");
					DadosVeiculo  dadosVeiculo = leituraDados.jsonToObject(json, DadosVeiculo.class);
					veiculos.add( new Veiculo(dadosVeiculo));
				}
		);

		veiculos.forEach(System.out::println);

	}
}
