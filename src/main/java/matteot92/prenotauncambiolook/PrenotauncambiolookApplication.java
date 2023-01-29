package matteot92.prenotauncambiolook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import matteot92.prenotauncambiolook.model.service.UtenteService;

@SpringBootApplication
public class PrenotauncambiolookApplication implements CommandLineRunner{

	public static void main(String[] args){
		SpringApplication.run(PrenotauncambiolookApplication.class, args);
	}

	@Autowired
	private UtenteService service;
	
	@Override
	public void run(String... args) throws Exception {
		
	}
	
}
