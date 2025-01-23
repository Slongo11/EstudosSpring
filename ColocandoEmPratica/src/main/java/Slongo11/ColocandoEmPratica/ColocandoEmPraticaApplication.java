package Slongo11.ColocandoEmPratica;

import Slongo11.ColocandoEmPratica.principal.PrincipalApp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ColocandoEmPraticaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ColocandoEmPraticaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PrincipalApp principalApp = new PrincipalApp();
		principalApp.executa();
	}
}
