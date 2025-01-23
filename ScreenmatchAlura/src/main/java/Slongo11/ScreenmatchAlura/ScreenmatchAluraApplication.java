package Slongo11.ScreenmatchAlura;

import Slongo11.ScreenmatchAlura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScreenmatchAluraApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchAluraApplication.class, args);
	}

}
