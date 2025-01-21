package Slongo11.ScreenmatchAlura;

import Slongo11.ScreenmatchAlura.model.DadosSerie;
import Slongo11.ScreenmatchAlura.service.ConsumoAPI;
import Slongo11.ScreenmatchAlura.service.ConverterDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScreenmatchAluraApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		ConsumoAPI consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		//var	json = consumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		ConverterDados conversor = new ConverterDados();
		DadosSerie dados = conversor.obertDados(json, DadosSerie.class);
		System.out.println(dados);
	}

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchAluraApplication.class, args);
	}

}
