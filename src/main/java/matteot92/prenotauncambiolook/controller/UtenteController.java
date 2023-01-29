package matteot92.prenotauncambiolook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import matteot92.prenotauncambiolook.model.service.UtenteService;

@Controller
public class UtenteController {

	private UtenteService service;
	
	@Autowired
	public UtenteController(UtenteService service) {
		this.service = service;
	}

}
