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
	
	/**
	 * Metodo che consente di ritornare una lista di utenti registrati sul sito
	 * presenti sul database
	 */
	public List<Utente> utentiRegistrati() {
		return (List<Utente>) repository.findAll();
	}
	
	/**
	 * Metodo che consente di verificare, tramite username, email e password, se l'utente
	 * è registrato sul sito oppure no.
	 */
	public Boolean isRegistrato(Utente utenteDaVerificare) {
		Optional<Utente> utente = repository.findByUsernameAndEmailAndPassword(utenteDaVerificare.getUsername(), utenteDaVerificare.getEmail(), utenteDaVerificare.getPassword());
		return (!utente.isEmpty()) ? true : false;
	}
	
	/**
	 * Metodo che consente ad un utente già registrato di modificare
	 * la sua password con una nuova, salvandola sul database
	 */
	public Utente modificaPassword(Utente utente, String nuovaPassword) {
		utente.setPassword(nuovaPassword);
		return repository.save(utente);
	}
	
	/**
	 * Metodo che consente ad un nuovo utente di potersi registrare al sito
	 * salvando i suoi dati sul database
	 */
	public Utente registraUtente(Utente utente) {
		utente.setIsAdmin(false);
		return repository.save(utente);
	}
	
	/**
	 * Metodo che consente ad un cliente già registrato di potersi disiscriversi
	 * cancellandosi dal database del sito
	 */
	public void rimuoviUtente(Utente utente) {
		repository.delete(utente);
	}
	
	/**
	 * Metodo che verifica se l'utente ha il ruolo di admin oppure no,
	 * recuperandone l'informazione attraverso username e email dal database
	 */
	public Boolean isAdmin(Utente utenteDaVerificare) {
		Optional<Utente> utente = repository.findByUsernameAndEmail(utenteDaVerificare.getUsername(), utenteDaVerificare.getEmail());
		return (!utente.isEmpty()) ? utente.get().getIsAdmin() : null;
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'username e email
	 */
	public Utente cercaUtente(String username, String email) {
		return repository.findByUsernameAndEmail(username, email).get();
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'username, email e password
	 */
	public Utente cercaUtente(String username, String email, String password) {
		return repository.findByUsernameAndEmailAndPassword(username, email, password).get();
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'username
	 */
	public Utente cercaUtenteDaUsername(String username) {
		return repository.findByUsername(username).get();
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'email
	 */
	public Utente cercaUtenteDaEmail(String email) {
		return repository.findByEmail(email).get();
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'id
	 */
	public Utente cercaUtenteDaId(Long id) {
		return repository.findById(id).get();
	}
	
	/**
	 * Metodo che recupera la password di un utente attraverso il suo username e email
	 */
	public String recuperaPassword(String username, String email) {
		return repository.getPasswordByUsernameAndEmail(username, email);
	}

}
