package matteot92.prenotauncambiolook.model.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import matteot92.prenotauncambiolook.model.entities.Servizio;
import matteot92.prenotauncambiolook.model.repository.ServizioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServizioServiceTest {
	
	@Autowired
	private ServizioRepository repo;
	@Autowired
	private ServizioService service;

	@Test
	public void testServiziOfferti() {
		List<Servizio> servizi = service.serviziOfferti();
		assertTrue(servizi.size() > 0);
	}

	@Test
	public void testModificaPrezzo() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Tintura Blonde extreme");
		servizio.setId(5L);
		servizio.setPrezzo(30.0);
		Double nuovoPrezzo = 35.0;
		assertNotNull(service.modificaPrezzo(servizio, nuovoPrezzo));
	}
	
	@Test
	public void testModificaPrezzoNullParams() {
		Servizio servizio = null;
		Double nuovoPrezzo = null;
		assertNull(service.modificaPrezzo(servizio, nuovoPrezzo));
	}
	
	@Test
	public void testModificaPrezzoNullServizio() {
		Servizio servizio = null;
		Double nuovoPrezzo = 5.0;
		assertNull(service.modificaPrezzo(servizio, nuovoPrezzo));
	}
	
	@Test
	public void testModificaPrezzoNullPrezzo() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Taglio");
		servizio.setId(1L);
		servizio.setPrezzo(10.0);
		Double nuovoPrezzo = null;
		assertNotNull(service.modificaPrezzo(servizio, nuovoPrezzo));
	}
	
	@Test
	public void testModificaPrezzoNotFoundServizio() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Depilazione");
		servizio.setId(10L);
		servizio.setPrezzo(50.0);
		Double nuovoPrezzo = 100.0;
		assertNull(service.modificaPrezzo(servizio, nuovoPrezzo));
	}

	@Test
	public void testModificaDescrizione() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Tintura dark");
		servizio.setId(6L);
		servizio.setPrezzo(15.0);
		String nuovaDescrizione = "Tintura Dark";
		assertNotNull(service.modificaDescrizione(servizio, nuovaDescrizione));
	}
	
	@Test
	public void testModificaDescrizioneNullParams() {
		Servizio servizio = null;
		String nuovaDescrizione = null;
		assertNull(service.modificaDescrizione(servizio, nuovaDescrizione));
	}
	
	@Test
	public void testModificaDescrizioneNullServizio() {
		Servizio servizio = null;
		String nuovaDescrizione = "Test";
		assertNull(service.modificaDescrizione(servizio, nuovaDescrizione));
	}
	
	@Test
	public void testModificaDescrizioneNullDescrizione() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Taglio classico");
		servizio.setId(1L);
		servizio.setPrezzo(10.0);
		String nuovaDescrizione = null;
		assertNull(service.modificaDescrizione(servizio, nuovaDescrizione));
	}
	
	@Test
	public void testModificaDescrizioneNotFoundServizio() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Ceretta brasiliana");
		servizio.setId(508L);
		servizio.setPrezzo(500.0);
		String nuovaDescrizione = "Ceretta extreme";
		assertNull(service.modificaDescrizione(servizio, nuovaDescrizione));
	}

	@Test
	public void testModificaDescrizionePrezzo() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Tintura");
		servizio.setId(4L);
		servizio.setPrezzo(20.0);
		String nuovaDescrizione = "Tintura Castano";
		Double nuovoPrezzo = 15.0;
		assertNotNull(service.modificaDescrizionePrezzo(servizio, nuovaDescrizione, nuovoPrezzo));
	}
	
	@Test
	public void testModificaDescrizionePrezzoNullParamsButNotServizio() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Taglio");
		servizio.setId(1L);
		servizio.setPrezzo(10.0);
		String nuovaDescrizione = null;
		Double nuovoPrezzo = null;
		assertNotNull(service.modificaDescrizionePrezzo(servizio, nuovaDescrizione, nuovoPrezzo));
	}
	
	@Test
	public void testModificaDescrizionePrezzoNullParams() {
		Servizio servizio = null;
		String nuovaDescrizione = null;
		Double nuovoPrezzo = null;
		assertNull(service.modificaDescrizionePrezzo(servizio, nuovaDescrizione, nuovoPrezzo));
	}
	
	@Test
	public void testModificaDescrizionePrezzoNotFoundServizio() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Depilazione Ultimate");
		servizio.setId(1000L);
		servizio.setPrezzo(5000.0);
		String nuovaDescrizione = "Depilazione Ultimate Extreme";
		Double nuovoPrezzo = 1000.0;
		assertNull(service.modificaDescrizionePrezzo(servizio, nuovaDescrizione, nuovoPrezzo));
	}

	@Test
	public void testAggiungiServizio() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Taglio anni '90");
		servizio.setPrezzo(90.0);
		assertNotNull(service.aggiungiServizio(servizio));
	}
	
	@Test
	public void testAggiungiServizioNullParam() {
		Servizio servizio = null;
		assertNull(service.aggiungiServizio(servizio));
	}
	
	@Test
	public void testAggiungiServizioGiaPresente() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Mesh");
		servizio.setPrezzo(15.0);
		assertNull(service.aggiungiServizio(servizio));
	}

	@Test
	public void testRimuoviServizio() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Tintura");
		servizio.setPrezzo(20.0);
		assertDoesNotThrow(() -> service.rimuoviServizio(servizio));
	}
	
	@Test
	public void testRimuoviServizioNullParam() {
		Servizio servizio = null;
		assertDoesNotThrow(() -> service.rimuoviServizio(servizio));
	}
	
	@Test
	public void testRimuoviServizioNotFoundServizio() {
		Servizio servizio = new Servizio();
		servizio.setDescrizione("Tintura");
		servizio.setPrezzo(20.0);
		assertDoesNotThrow(() -> service.rimuoviServizio(servizio));
	}

	@Test
	public void testCercaServizio() {
		Long id = 1L;
		assertNotNull(service.cercaServizio(id));
	}
	
	@Test
	public void testCercaServizioNullParam() {
		Long id = null;
		assertNull(service.cercaServizio(id));
	}
	
	@Test
	public void testCercaServizioNotFoundServizio() {
		Long id = 100L;
		assertNull(service.cercaServizio(id));
	}

	@Test
	public void testCercaServizioPerDescrizione() {
		String descrizione = "Tintura dark";
		assertNotNull(service.cercaServizioPerDescrizione(descrizione));
	}
	
	@Test
	public void testCercaServizioPerDescrizioneNullParam() {
		String descrizione = null;
		assertNull(service.cercaServizioPerDescrizione(descrizione));
	}
	
	@Test
	public void testCercaServizioPerDescrizioneNotFoundServizio() {
		String descrizione = "Tintura brown";
		assertNull(service.cercaServizioPerDescrizione(descrizione));
	}

	@Test
	public void testCercaServizioPerPrezzo() {
		Double prezzo = 35.0;
		List<Servizio> serviziConStessoPrezzo = service.cercaServizioPerPrezzo(prezzo);
		assertEquals(1, serviziConStessoPrezzo.size());
	}
	
	@Test
	public void testCercaServizioPerPrezzoNullParam() {
		Double prezzo = null;
		List<Servizio> serviziConStessoPrezzo = service.cercaServizioPerPrezzo(prezzo);
		assertEquals(0, serviziConStessoPrezzo.size());
	}
	
	@Test
	public void testCercaServizioPerPrezzoNotFoundServizio() {
		Double prezzo = 5000.0;
		List<Servizio> serviziConStessoPrezzo = service.cercaServizioPerPrezzo(prezzo);
		assertEquals(0, serviziConStessoPrezzo.size());
	}

	@Test
	public void testCercaServizioPerDescrizionePrezzo() {
		String descrizione = "Taglio";
		Double prezzo = 10.0;
		Servizio servizio = service.cercaServizioPerDescrizionePrezzo(descrizione, prezzo);
		assertNotNull(servizio);
	}
	
	@Test
	public void testCercaServizioPerDescrizionePrezzoNullParams() {
		String descrizione = null;
		Double prezzo = null;
		Servizio servizio = service.cercaServizioPerDescrizionePrezzo(descrizione, prezzo);
		assertNull(servizio);
	}
	
	@Test
	public void testCercaServizioPerDescrizionePrezzoNotFoundServizio() {
		String descrizione = "Test";
		Double prezzo = 599.0;
		Servizio servizio = service.cercaServizioPerDescrizionePrezzo(descrizione, prezzo);
		assertNull(servizio);
	}

}
