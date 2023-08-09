package matteot92.prenotauncambiolook.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.repository.UtenteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtenteServiceTest {
	
	@Autowired
	private UtenteRepository repo;
	@Autowired
	private UtenteService service;

	@Test
	public void testUtentiRegistrati() {
		List<Utente> utenti = service.utentiRegistrati();
		assertEquals(true, utenti.size() > 0);
	}

	@Test
	public void testIsRegistrato() {
		Utente matteo = new Utente();
		matteo.setEmail("prova@prova.it");
		matteo.setId(2L);
		matteo.setIsAdmin(false);
		matteo.setPassword("prova");
		matteo.setUsername("Matteo");
		Boolean risposta = service.isRegistrato(matteo);
		assertTrue(risposta);
	}
	
	@Test
	public void testIsRegistratoNullParam() {
		Utente utente = null;
		assertDoesNotThrow(() -> service.isRegistrato(utente));
		assertFalse(service.isRegistrato(utente));
	}
	
	@Test
	public void testIsRegistratoNotFoundUtente() {
		Utente utente = new Utente();
		utente.setEmail("maria@prova.it");
		utente.setId(44L);
		utente.setIsAdmin(false);
		utente.setPassword("maria");
		utente.setUsername("Maria");
		assertDoesNotThrow(() -> service.isRegistrato(utente));
		assertFalse(service.isRegistrato(utente));
	}

	@Test
	public void testModificaPassword() {
		Utente utente = new Utente();
		utente.setEmail("seppy@maria.it");
		utente.setId(24L);
		utente.setIsAdmin(false);
		utente.setPassword("test");
		utente.setUsername("Seppy");
		String password = "seppy";
		Utente utenteConModifiche = service.modificaPassword(utente, password);
		assertNotNull(utenteConModifiche);
	}
	
	@Test
	public void testModificaPasswordNotFoundUtente() {
		Utente utente = new Utente();
		utente.setEmail("maria@prova.it");
		utente.setId(44L);
		utente.setIsAdmin(false);
		utente.setPassword("maria");
		utente.setUsername("Maria");
		String password = "prova";
		Utente utenteConModifiche = service.modificaPassword(utente, password);
		assertNull(utenteConModifiche);
	}
	
	@Test
	public void testModificaPasswordNullParam() {
		Utente matteo = new Utente();
		matteo.setEmail("prova@prova.it");
		matteo.setId(2L);
		matteo.setIsAdmin(false);
		matteo.setPassword("prova");
		matteo.setUsername("Matteo");
		String password = null;
		assertDoesNotThrow(() -> service.modificaPassword(matteo, password));
		Utente utenteDopoModifiche = service.modificaPassword(matteo, password);
		assertEquals(matteo, utenteDopoModifiche);
	}
	
	@Test
	public void testModificaPasswordNullParams() {
		String password = null;
		Utente utente = null;
		assertDoesNotThrow(() -> service.modificaPassword(utente, password));
		Utente utenteDopoModifiche = service.modificaPassword(utente, password);
		assertNull(utenteDopoModifiche);
	}

	@Test
	public void testRegistraUtente() {
		Utente utente = new Utente();
		utente.setEmail("seppy@maria.it");
		utente.setPassword("test");
		utente.setUsername("Seppy");
		assertDoesNotThrow(() -> service.registraUtente(utente));
	}
	
	@Test
	public void testRegistraUtenteNullParam() {
		Utente utente = null;
		assertDoesNotThrow(() -> service.registraUtente(utente));
		assertNull(service.registraUtente(utente));
	}
	
	@Test
	public void testRegistraUtenteGiaRegistrato() {
		Utente matteo = new Utente();
		matteo.setEmail("prova@prova.it");
		matteo.setId(2L);
		matteo.setIsAdmin(false);
		matteo.setPassword("prova");
		matteo.setUsername("Matteo");
		assertDoesNotThrow(() -> service.registraUtente(matteo));
		assertEquals(matteo, service.registraUtente(matteo));
	}

	@Test
	public void testRimuoviUtente() {
		Utente mario = new Utente();
		mario.setEmail("mario@mario.it");
		mario.setPassword("mario");
		mario.setUsername("Mario");
		mario.setId(4L);
		mario.setIsAdmin(false);
		assertDoesNotThrow(() -> service.rimuoviUtente(mario));
	}
	
	@Test
	public void testRimuoviUtenteNullParam() {
		Utente utente = null;
		assertDoesNotThrow(() -> service.rimuoviUtente(utente));
	}
	
	@Test
	public void testRimuoviUtenteThatNotExists() {
		Utente utente = new Utente();
		utente.setEmail("marta@prova.it");
		utente.setPassword("marta");
		utente.setUsername("Marta");
		utente.setId(400L);
		utente.setIsAdmin(false);
		assertDoesNotThrow(() -> service.rimuoviUtente(utente));
	}

	@Test
	public void testIsAdmin() {
		Utente matteo = new Utente();
		matteo.setEmail("prova@prova.it");
		matteo.setPassword("prova");
		matteo.setUsername("Matteo");
		matteo.setId(2L);
		matteo.setIsAdmin(false);
		assertDoesNotThrow(() -> service.isAdmin(matteo));
		assertFalse(service.isAdmin(matteo));
	}
	
	@Test
	public void testIsAdminNullParam() {
		Utente utente = null;
		assertDoesNotThrow(() -> service.isAdmin(utente));
		assertNull(service.isAdmin(utente));
	}
	
	@Test
	public void testIsAdminNotFoundUtente() {
		Utente utente = new Utente();
		utente.setEmail("maria@prova.it");
		utente.setId(44L);
		utente.setIsAdmin(false);
		utente.setPassword("maria");
		utente.setUsername("Maria");
		assertDoesNotThrow(() -> service.isAdmin(utente));
		assertNull(service.isAdmin(utente));
	}

	@Test
	public void testCercaUtenteStringString() {
		String username = "Matteo";
		String email = "prova@prova.it";
		assertDoesNotThrow(() -> service.cercaUtente(username, email));
		Utente utenteCercato = service.cercaUtente(username, email);
		assertNotNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteStringStringNullParams() {
		String username = null;
		String email = null;
		assertDoesNotThrow(() -> service.cercaUtente(username, email));
		Utente utenteCercato = service.cercaUtente(username, email);
		assertNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteStringStringNotFoundUtente() {
		String username = "Magnus";
		String email = "magnus@prova.it";
		assertDoesNotThrow(() -> service.cercaUtente(username, email));
		Utente utenteCercato = service.cercaUtente(username, email);
		assertNull(utenteCercato);
	}

	@Test
	public void testCercaUtenteStringStringString() {
		String username = "Matteo";
		String email = "prova@prova.it";
		String password = "prova";
		assertDoesNotThrow(() -> service.cercaUtente(username, email, password));
		Utente utenteCercato = service.cercaUtente(username, email, password);
		assertNotNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteStringStringStringNullParams() {
		String username = null;
		String email = null;
		String password = null;
		assertDoesNotThrow(() -> service.cercaUtente(username, email, password));
		Utente utenteCercato = service.cercaUtente(username, email, password);
		assertNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteStringStringStringNotFoundUtente() {
		String username = "Magnus";
		String email = "magnus@prova.it";
		String password = "magnus";
		assertDoesNotThrow(() -> service.cercaUtente(username, email, password));
		Utente utenteCercato = service.cercaUtente(username, email, password);
		assertNull(utenteCercato);
	}

	@Test
	public void testCercaUtenteDaUsername() {
		String username = "Matteo";
		assertDoesNotThrow(() -> service.cercaUtenteDaUsername(username));
		Utente utenteCercato = service.cercaUtenteDaUsername(username);
		assertNotNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteDaUsernameNullParam() {
		String username = null;
		assertDoesNotThrow(() -> service.cercaUtenteDaUsername(username));
		Utente utenteCercato = service.cercaUtenteDaUsername(username);
		assertNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteDaUsernameNotFoundUtente() {
		String username = "Magnus";
		assertDoesNotThrow(() -> service.cercaUtenteDaUsername(username));
		Utente utenteCercato = service.cercaUtenteDaUsername(username);
		assertNull(utenteCercato);
	}

	@Test
	public void testCercaUtenteDaEmail() {
		String email = "prova@prova.it";
		assertDoesNotThrow(() -> service.cercaUtenteDaEmail(email));
		Utente utenteCercato = service.cercaUtenteDaEmail(email);
		assertNotNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteDaEmailNullParam() {
		String email = null;
		assertDoesNotThrow(() -> service.cercaUtenteDaEmail(email));
		Utente utenteCercato = service.cercaUtenteDaEmail(email);
		assertNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteDaEmailNotFoundUtente() {
		String email = "mario@mario.it";
		assertDoesNotThrow(() -> service.cercaUtenteDaEmail(email));
		Utente utenteCercato = service.cercaUtenteDaEmail(email);
		assertNull(utenteCercato);
	}

	@Test
	public void testCercaUtenteDaId() {
		Long id = 2L;
		assertDoesNotThrow(() -> service.cercaUtenteDaId(id));
		Utente utenteCercato = service.cercaUtenteDaId(id);
		assertNotNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteDaIdNullParam() {
		Long id = null;
		assertDoesNotThrow(() -> service.cercaUtenteDaId(id));
		Utente utenteCercato = service.cercaUtenteDaId(id);
		assertNull(utenteCercato);
	}
	
	@Test
	public void testCercaUtenteDaIdNotFoundUtente() {
		Long id = 10L;
		assertDoesNotThrow(() -> service.cercaUtenteDaId(id));
		Utente utenteCercato = service.cercaUtenteDaId(id);
		assertNull(utenteCercato);
	}

	@Test
	public void testRecuperaPassword() {
		String username = "Matteo";
		String email = "prova@prova.it";
		String passwordAttesa = "prova";
		assertDoesNotThrow(() -> service.recuperaPassword(username, email));
		assertEquals(passwordAttesa, service.recuperaPassword(username, email));
	}
	
	@Test
	public void testRecuperaPasswordNullParams() {
		String username = null;
		String email = null;
		String passwordAttesa = null;
		assertDoesNotThrow(() -> service.recuperaPassword(username, email));
		assertEquals(passwordAttesa, service.recuperaPassword(username, email));
	}
	
	@Test
	public void testRecuperaPasswordNotFoundUtente() {
		String username = "Magnus";
		String email = "magnus@prova.it";
		String passwordAttesa = null;
		assertDoesNotThrow(() -> service.recuperaPassword(username, email));
		assertEquals(passwordAttesa, service.recuperaPassword(username, email));
	}

	@Test
	public void testDeviCambiarePassword() {
		Utente matteo = new Utente();
		matteo.setEmail("prova@prova.it");
		matteo.setPassword("prova");
		matteo.setUsername("Matteo");
		matteo.setId(2L);
		matteo.setIsAdmin(false);
		Boolean flag = false;
		assertDoesNotThrow(() -> service.deviCambiarePassword(matteo, flag));
	}
	
	@Test
	public void testDeviCambiarePasswordNullParams() {
		Utente utente = null;
		Boolean flag = null;
		assertNull(service.deviCambiarePassword(utente, flag));
	}
	
	@Test
	public void testDeviCambiarePasswordNullParamButNotUtente() {
		Utente matteo = new Utente();
		matteo.setEmail("prova@prova.it");
		matteo.setPassword("prova");
		matteo.setUsername("Matteo");
		matteo.setId(2L);
		matteo.setIsAdmin(false);
		Boolean flag = null;
		assertDoesNotThrow(() -> service.deviCambiarePassword(matteo, flag));
		assertNotNull(service.deviCambiarePassword(matteo, flag));
	}
	
	@Test
	public void testDeviCambiarePasswordNotFoundUtente() {
		Utente utente = new Utente();
		utente.setEmail("maria@prova.it");
		utente.setPassword("mario");
		utente.setUsername("Maria");
		utente.setId(11L);
		utente.setIsAdmin(false);
		Boolean flag = false;
		assertNull(service.deviCambiarePassword(utente, flag));
	}

}
