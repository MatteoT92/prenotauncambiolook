package matteot92.prenotauncambiolook.model.service;

import java.util.List;
import java.util.Optional;

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
		return (List<Utente>) repository.findAll();
	}
	
	public Boolean isRegistrato(Utente utenteDaVerificare) {
		Optional<Utente> utente = repository.findByUsernameAndEmail(utenteDaVerificare.getUsername(), utenteDaVerificare.getEmail());
		return (!utente.isEmpty()) ? true : false;
	}
	
	public Utente modificaPassword(Utente utente, String nuovaPassword) {
		utente.setPassword(nuovaPassword);
		return repository.save(utente);
	}
	
	public Utente registraUtente(Utente utente) {
		utente.setIsAdmin(false);
		return repository.save(utente);
	}
	
	public void rimuoviUtente(Utente utente) {
		repository.delete(utente);
	}
	
	public Boolean isAdmin(Utente utenteDaVerificare) {
		Optional<Utente> utente = repository.findByUsernameAndEmail(utenteDaVerificare.getUsername(), utenteDaVerificare.getEmail());
		return (!utente.isEmpty()) ? utente.get().getIsAdmin() : null;
	}
	
	public Utente cercaUtente(String username, String email) {
		return repository.findByUsernameAndEmail(username, email).get();
	}
	
	public Utente cercaUtenteDaUsername(String username) {
		return repository.findByUsername(username).get();
	}
	
	public Utente cercaUtenteDaEmail(String email) {
		return repository.findByEmail(email).get();
	}
	
}
