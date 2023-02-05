package matteot92.prenotauncambiolook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import matteot92.prenotauncambiolook.model.entities.Ordine;
import matteot92.prenotauncambiolook.model.service.OrdineService;

@Controller
@SessionAttributes({"appuntamenti"})
public class OrdineController {

	private OrdineService ordineService;
	
	@Autowired
	public OrdineController(OrdineService ordineService) {
		this.ordineService = ordineService;
	}
	
	@GetMapping("/admin/ordini")
	public List<Ordine> listaPrenotazioni(Model model) {
		model.addAttribute("appuntamenti", ordineService.listaOrdini());
		return ordineService.listaOrdini();
	}

}
