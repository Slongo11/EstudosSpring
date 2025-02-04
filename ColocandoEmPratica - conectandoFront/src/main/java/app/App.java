package app;

import Slongo11.ColocandoEmPratica.model.Dados;
import Slongo11.ColocandoEmPratica.model.Frase;
import Slongo11.ColocandoEmPratica.repository.FraseRepository;
import Slongo11.ColocandoEmPratica.service.ConsultaAPI;
import Slongo11.ColocandoEmPratica.service.ConverterDados;

import java.util.ArrayList;
import java.util.List;

public class App {
	private  FraseRepository fraseRepository;
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String API_KEY ="&apikey=6585022c";
	private ConsultaAPI consultaAPI = new ConsultaAPI();
	private ConverterDados conversor = new ConverterDados();
	private List<Frase> frases = new ArrayList<>();
	public App(FraseRepository fraseRepository){
		this.fraseRepository = fraseRepository;
	}
	public void popula(){
		var nomeSerie = "grey's anatomy";
		var frase ="Faça um plano, tenha um objetivo. Trabalhe para alcançá-los, mas de vez em quando, olhe ao seu redor e aproveite, porque é isso... Tudo pode acabar amanhã.";
		var personagem = "Meredith Grey";
		var json = consultaAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		System.out.println(json);
		Dados dados = conversor.obterDados(json, Dados.class);
		criaFrase(dados,frase,personagem);

		nomeSerie = "grey's anatomy";
		frase ="GreyQuando estamos com o coração em risco, nós respondemos de duas formas: nós iremos correr ou iremos atacar. Tem um termo científico para isso: lutar ou voar. É o instinto. Nós não podemos controlar. Ou podemos?";
		personagem = "Derek Shepherd";
		json = consultaAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		dados = conversor.obterDados(json, Dados.class);
		criaFrase(dados,frase,personagem);

		nomeSerie = "game of thrones";
		frase ="O inverno está chegando!";
		personagem = "Ned Stark";
		json = consultaAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		dados = conversor.obterDados(json, Dados.class);
		criaFrase(dados,frase,personagem);

		nomeSerie = "game of thrones";
		frase ="Nunca esqueça o que você é, o resto do mundo não esquecerá.";
		personagem = "Tyrion Lannister\n";
		json = consultaAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		dados = conversor.obterDados(json, Dados.class);
		criaFrase(dados,frase,personagem);

		nomeSerie = "game of thrones";
		frase ="Existe apenas um Deus e o nome dele é Morte. E existe só uma coisa que dizemos para a Morte: hoje não!";
		personagem = "Syrio Forel";
		json = consultaAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		dados = conversor.obterDados(json, Dados.class);
		criaFrase(dados,frase,personagem);

		fraseRepository.saveAll(frases);
	}
	private void criaFrase(Dados dados, String frase, String personagem){
		frases.add(new Frase(dados, frase, personagem));
	}
}
