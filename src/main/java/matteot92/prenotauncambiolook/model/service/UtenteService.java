package matteot92.prenotauncambiolook.model.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
		Optional<Utente> utente = Optional.empty();
		try {
			utente = repository.findByUsernameAndEmailAndPassword(utenteDaVerificare.getUsername(), utenteDaVerificare.getEmail(), utenteDaVerificare.getPassword());
		} catch (NullPointerException | NoSuchElementException e) {
			
		}
		return (!utente.isEmpty()) ? true : false;
	}
	
	/**
	 * Metodo che consente ad un utente già registrato di modificare
	 * la sua password con una nuova, salvandola sul database
	 */
	public Utente modificaPassword(Utente utente, String nuovaPassword) {
		Utente utentePasswordDaModificare = null;
		Boolean check = this.isRegistrato(utente); // verifica se è già registrato
		try {
			if (nuovaPassword != null && check == true) { // se il risultato è true procede alla modifica della password
				utente.setPassword(nuovaPassword);
				utentePasswordDaModificare = repository.save(utente);
			} else if (nuovaPassword == null && check == true) {
				utentePasswordDaModificare = utente; // altrimenti lascia invariati i dati
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			
		}
		return utentePasswordDaModificare;
	}
	
	/**
	 * Metodo che consente ad un nuovo utente di potersi registrare al sito
	 * salvando i suoi dati sul database
	 */
	public Utente registraUtente(Utente utente) {
		Utente nuovoUtente = null;
		Boolean check = this.isRegistrato(utente); // verifica se è già registrato
		try {
			if (check == false) { // se non lo è allora lo registra
				utente.setIsAdmin(false);
				nuovoUtente = repository.save(utente);
			} else {
				nuovoUtente = utente; // altrimenti non fa nulla
			}
		} catch (NullPointerException | IllegalArgumentException | DataIntegrityViolationException e) {
			
		}
		return nuovoUtente;
	}
	
	/**
	 * Metodo che consente ad un cliente già registrato di potersi disiscriversi
	 * cancellandosi dal database del sito
	 */
	public void rimuoviUtente(Utente utente) {
		try {
			repository.delete(utente);
		} catch (InvalidDataAccessApiUsageException | IllegalArgumentException e) {
			
		}
	}
	
	/**
	 * Metodo che verifica se l'utente ha il ruolo di admin oppure no,
	 * recuperandone l'informazione attraverso username e email dal database
	 */
	public Boolean isAdmin(Utente utenteDaVerificare) {
		Optional<Utente> utente = Optional.empty();
		try {
			utente = repository.findByUsernameAndEmail(utenteDaVerificare.getUsername(), utenteDaVerificare.getEmail());
		} catch (NullPointerException | NoSuchElementException e) {
			
		}
		return (!utente.isEmpty()) ? utente.get().getIsAdmin() : null;
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'username e email
	 */
	public Utente cercaUtente(String username, String email) {
		Utente utente = null;
		try {
			utente = repository.findByUsernameAndEmail(username, email).get();
		} catch (NullPointerException | NoSuchElementException e) {
			
		}
		return utente;
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'username, email e password
	 */
	public Utente cercaUtente(String username, String email, String password) {
		Utente utente = null;
		try {
			utente = repository.findByUsernameAndEmailAndPassword(username, email, password).get(); 
		} catch (NullPointerException | NoSuchElementException e) {
			
		}
		return utente;
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'username
	 */
	public Utente cercaUtenteDaUsername(String username) {
		Utente utente = null;
		try {
			utente = repository.findByUsername(username).get();
		} catch (NullPointerException | NoSuchElementException e) {
			
		}
		return utente;
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'email
	 */
	public Utente cercaUtenteDaEmail(String email) {
		Utente utente = null;
		try {
			utente = repository.findByEmail(email).get();
		} catch (NullPointerException | NoSuchElementException e) {
			
		}
		return utente;
	}
	
	/**
	 * Metodo che recupera un utente dal database in base all'id
	 */
	public Utente cercaUtenteDaId(Long id) {
		Utente utente = null;
		try {
			utente = repository.findById(id).get();
		} catch (NullPointerException | NoSuchElementException | IllegalArgumentException | InvalidDataAccessApiUsageException e) {
			
		}
		return utente;
	}
	
	/**
	 * Metodo che recupera la password di un utente attraverso il suo username e email
	 */
	public String recuperaPassword(String username, String email) {
		return repository.getPasswordByUsernameAndEmail(username, email);
	}
	
	/**
	 * Metodo che genera una password random per l'utente che richiede il recupero nel form Angular
	 */
	public String generaPassword() {
	    //alphabet contiene tutti i possibili caratteri che comporranno la Password
		String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz?!<>-*[]{}/";
		int alphabetLength = alphabet.length();
		String password = "";
		for (int i = 0; i < 8; i++) { // la password provvisoria sarà di 8 caratteri
		    // Scelgo una delle lettere dell'alfabeto
		    int randomIndexCharInAlphabet = (int)(Math.random()*alphabetLength);
		    password += alphabet.charAt(randomIndexCharInAlphabet);
		}
		return password;
	}
	
	/**
	 * Metodo che altera il flag del database che indica se deve o non deve cambiare password
	 */
	public Utente deviCambiarePassword(Utente utente, Boolean flag) {
		Utente utenteDaCambiarePassword = null;
		Boolean check = this.isRegistrato(utente); // verifica se è già registrato
		try {
			if (flag != null && check == true) { // se il risultato è true allora inserisce un flag che segna che deve cambiare password in una più sicura
				utente.setCambiaPassword(flag);
				utenteDaCambiarePassword = repository.save(utente);
			} else if (flag == null && check == true) { // altrimenti non fa nulla
				utenteDaCambiarePassword = utente;
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			
		}
		return utenteDaCambiarePassword;
	}

}
