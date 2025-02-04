//package Slongo11.ScreenmatchAlura;
//
//import Slongo11.ScreenmatchAlura.principal.Principal;
//import Slongo11.ScreenmatchAlura.repository.SerieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//
//@SpringBootApplication
//public class ScreenmatchAluraApplicationSemWeb implements CommandLineRunner {
//	@Autowired
//	private SerieRepository repository;
//	@Override
//	public void run(String... args) throws Exception {
//		Principal principal = new Principal(repository);
//		principal.exibeMenu();
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(ScreenmatchAluraApplicationSemWeb.class, args);
//	}
//
//}
