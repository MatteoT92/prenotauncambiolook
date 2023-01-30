package matteot92.prenotauncambiolook;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import matteot92.prenotauncambiolook.model.entities.Ordine;
import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.service.OrdineService;
import matteot92.prenotauncambiolook.model.service.ServizioService;
import matteot92.prenotauncambiolook.model.service.UtenteService;

@SpringBootApplication
public class PrenotauncambiolookApplication implements CommandLineRunner{

	public static void main(String[] args){
		SpringApplication.run(PrenotauncambiolookApplication.class, args);
	}

	@Autowired
	private UtenteService serviceUtente;
	@Autowired
	private ServizioService serviceServizio;
	@Autowired
	private OrdineService serviceOrdine;
	
	@Override
	public void run(String... args) throws Exception {
		
		Utente admin = new Utente();
		admin.setEmail("admin@prova.it");
		admin.setIsAdmin(true);
		admin.setUsername("Matteo");
		admin.setPassword("admin");
		
		//admin = serviceUtente.registraUtente(admin);
		
		Servizio taglio = new Servizio();
		taglio.setId("A001");
		taglio.setDescrizione("Taglio capelli");
		taglio.setPrezzo(5.00);
		
		//taglio = serviceServizio.aggiungiServizio(taglio);
		
		Ordine ordine = new Ordine();
		ordine.setData(LocalDate.of(2023,1,30));
		ordine.setOrario(LocalTime.of(12, 30, 0));
		ordine.setQuantita(1);
		ordine.setServizio(taglio);
		ordine.setUtente(admin);
		
		ordine = serviceOrdine.salvaOrdine(ordine.getData(), 
											ordine.getOrario(), 
											ordine.getQuantita(),
											ordine.getUtente(),
											ordine.getServizio());
	}
	
}
