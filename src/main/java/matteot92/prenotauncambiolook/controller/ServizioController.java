package matteot92.prenotauncambiolook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.service.ServizioService;

//@Controller
@RestController
@SessionAttributes({"servizi", "descrizione", "prezzo"})
@CrossOrigin(origins = "http://localhost:4200")
public class ServizioController {

	private ServizioService servizioService;
	
	@Autowired
	public ServizioController(ServizioService servizioService) {
		this.servizioService = servizioService;
	}
	
	// UTENTE CLIENTE
	
	/**
	 * Metodo che mostra ad un utente cliente tutti i servizi offerti dal salone
	 */
	@GetMapping("/servizi")
	public List<Servizio> serviziDisponibili(Model model) {
		model.addAttribute("servizi", servizioService.serviziOfferti());
		return servizioService.serviziOfferti();
	}
	
	// UTENTE ADMIN
	
	/**
	 * Metodo che reindirizza un utente admin sulla pagina dei servizi
	 */
	@GetMapping("/admin/servizi")
	public String servizi() {
		return "admin/servizi";
	}
	
	/**
	 * Metodo che permette ad un admin di poter aggiungere nuovi servizi compilando il form
	 * e il nuovo servizio verrà salvato sul database.
	 * L'admin verrà poi reindirizzato sulla pagina del pannello di controllo
	 */
	@PostMapping("/admin/servizi")
	public String aggiungiServizio(@ModelAttribute("servizio") Servizio servizio) {
		servizioService.aggiungiServizio(servizio);
		return "redirect:/admin/pannello";
	}
	
}
