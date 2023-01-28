package matteot92.prenotauncambiolook.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.repository.UtenteRepository;

@Service
public class UtenteService {
	
	private UtenteRepository repository;
	
	@Autowired
	public UtenteService(UtenteRepository repository) {
		this.repository = repository;
	}
	
	public List<Utente> utentiRegistrati() {
		return repository.findAll();
	}
	
}
