package matteot92.prenotauncambiolook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.service.ServizioService;

@Controller
public class ServizioController {

	private ServizioService servizioService;
	
	@Autowired
	public ServizioController(ServizioService servizioService) {
		this.servizioService = servizioService;
	}
	
	@GetMapping("/servizi")
	public List<Servizio> serviziDisponibili(Model model) {
		model.addAttribute("servizi", servizioService.serviziOfferti());
		return servizioService.serviziOfferti();
	}
	
	@GetMapping("/admin/servizi")
	public String servizi() {
		return "admin/servizi";
	}
	
	@PostMapping("/admin/servizi")
	public String aggiungiServizio(@ModelAttribute("servizio") Servizio servizio) {
		servizioService.aggiungiServizio(servizio);
		return "redirect:/admin/pannello";
	}
}
