package matteot92.prenotauncambiolook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import matteot92.prenotauncambiolook.model.entities.Utente;
import matteot92.prenotauncambiolook.model.repository.UtenteRepository;
import matteot92.prenotauncambiolook.model.service.UtenteService;

@RunWith(MockitoJUnitRunner.class)
public class UtenteServiceTest {
	
	@Mock
	private UtenteRepository repo;
	@InjectMocks
	private UtenteService service;
	
	private Utente utente1 = new Utente();
	private Utente utente2 = new Utente();
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		utente1.setId((long) 1);
		utente2.setId((long) 2);
		utente1.setUsername("Admin");
		utente2.setUsername("Matteo");
		utente1.setEmail("admin@admin.it");
		utente2.setEmail("prova@prova.it");
		utente1.setPassword("admin");
		utente2.setPassword("prova");
		utente1.setIsAdmin(true);
		utente2.setIsAdmin(false);
	}

	@Test
	public void testUtentiRegistrati() {
		List<Utente> utenti = new ArrayList();
		utenti.add(utente1);
		utenti.add(utente2);
		when(repo.findAll()).thenReturn(utenti);
		assertEquals(utenti, service.utentiRegistrati());
		verify(repo).findAll();
	}

	@Test
	public void testIsRegistratoEmptyParam() {
		Optional<Utente> utente = Optional.empty();
		lenient().when(repo.findByUsernameAndEmailAndPassword("Matteo", "prova@prova.it", "prova")).thenReturn(utente);
		assertThrows(NoSuchElementException.class, () -> service.isRegistrato(utente.get()));
		//verify(repo).findByUsernameAndEmailAndPassword("Matteo", "prova@prova.it", "prova");
	}
	
	@Test
	public void testIsRegistrato() {
		Optional<Utente> utente = Optional.of(utente2);
		when(repo.findByUsernameAndEmailAndPassword("Matteo", "prova@prova.it", "prova")).thenReturn(utente);
		assertEquals(true, service.isRegistrato(utente.get()));
		verify(repo).findByUsernameAndEmailAndPassword("Matteo", "prova@prova.it", "prova");
	}
	
	@Test
	public void testModificaPasswordNullParams() {
		String password = null;
		Utente utente = null;
		lenient().when(repo.save(utente)).thenThrow(IllegalArgumentException.class);
		assertDoesNotThrow(() -> service.modificaPassword(utente, password));
		//verify(repo).save(utente);
	}

	@Test
	public void testModificaPassword() {
		String password = "ciao";
		Utente utente = new Utente();
		utente.setPassword(password);
		utente.setCambiaPassword(utente2.getCambiaPassword());
		utente.setEmail(utente2.getEmail());
		utente.setId(utente2.getId());
		utente.setIsAdmin(utente2.getIsAdmin());
		utente.setUsername(utente2.getUsername());
		when(repo.save(utente2)).thenReturn(utente);
		assertEquals(utente, service.modificaPassword(utente2, password));
		verify(repo).save(utente2);
	}

	@Test
	public void testRegistraUtenteNullParam() {
		Utente utente = null;
		lenient().when(repo.save(utente)).thenThrow(IllegalArgumentException.class);
		assertDoesNotThrow(() -> service.registraUtente(utente));
		//verify(repo).save(utente);
	}
	
	@Test
	public void testRegistraUtente() {
		lenient().when(repo.save(utente2)).thenReturn(utente2);
		assertEquals(utente2, service.registraUtente(utente2));
		verify(repo).save(utente2);
	}
	
	@Test
	public void testRimuoviUtenteNullParam() {
		doThrow(new IllegalArgumentException()).when(repo).delete(null);
		service.rimuoviUtente(null);
		verify(repo).delete(null);
	}

	@Test
	public void testRimuoviUtente() {
		doNothing().when(repo).delete(utente2);
		service.rimuoviUtente(utente2);
		verify(repo).delete(utente2);
	}
	
	@Test
	public void testIsAdminEmptyParam() {
		Optional<Utente> utente = Optional.empty();
		lenient().when(repo.findByUsernameAndEmail("Matteo", "prova@prova.it")).thenThrow(NoSuchElementException.class);
		assertThrows(NoSuchElementException.class, () -> service.isAdmin(utente.get()));
		//verify(repo).findByUsernameAndEmail("Matteo", "prova@prova.it");
	}

	@Test
	public void testIsAdmin() {
		//Optional<Utente> utente = Optional.of(utente1);
		Optional<Utente> utente = Optional.of(utente2);
		lenient().when(repo.findByUsernameAndEmail("Matteo", "prova@prova.it")).thenReturn(utente);
		//assertEquals(true, service.isRegistrato(utente.get()));
		assertEquals(false, service.isAdmin(utente.get()));
		verify(repo).findByUsernameAndEmail("Matteo", "prova@prova.it");
	}
	
	@Test
	public void testCercaUtenteNullParams() {
		when(repo.findByUsernameAndEmail(null, null)).thenReturn(null);
		assertNull(service.cercaUtente(null, null));
		verify(repo).findByUsernameAndEmail(null, null);
	}

	@Test
	public void testCercaUtente() {
		Optional<Utente> utente = Optional.of(utente2);
		when(repo.findByUsernameAndEmail("Matteo", "prova@prova.it")).thenReturn(utente);
		assertEquals(utente.get(), service.cercaUtente("Matteo", "prova@prova.it"));
		verify(repo).findByUsernameAndEmail("Matteo", "prova@prova.it");
	}
	
	@Test
	public void testRecuperaPasswordNullParams() {
		when(repo.getPasswordByUsernameAndEmail(null, null)).thenReturn(null);
		assertNull(service.recuperaPassword(null, null));
		verify(repo).getPasswordByUsernameAndEmail(null, null);
	}

	@Test
	public void testRecuperaPassword() {
		Optional<Utente> utente = Optional.of(utente2);
		String password = "prova";
		when(repo.getPasswordByUsernameAndEmail("Matteo", "prova@prova.it")).thenReturn(utente.get().getPassword());
		assertEquals(password, service.recuperaPassword("Matteo", "prova@prova.it"));
		verify(repo).getPasswordByUsernameAndEmail("Matteo", "prova@prova.it");
	}

	@Test
	public void testDeviCambiarePassword() {
		Boolean flag = true;
		Utente utente = new Utente();
		utente.setPassword(utente2.getPassword());
		utente.setCambiaPassword(flag);
		utente.setEmail(utente2.getEmail());
		utente.setId(utente2.getId());
		utente.setIsAdmin(utente2.getIsAdmin());
		utente.setUsername(utente2.getUsername());
		when(repo.save(utente2)).thenReturn(utente);
		assertEquals(utente, service.deviCambiarePassword(utente2, flag));
		verify(repo).save(utente2);
	}

}
