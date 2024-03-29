package matteot92.prenotauncambiolook.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import matteot92.prenotauncambiolook.model.entities.Servizio;

@Repository
public interface ServizioRepository extends CrudRepository<Servizio, Long> {

	public Servizio findByDescrizione(String descrizione);
	public List<Servizio> findByPrezzo(Double prezzo);
	public Servizio findByDescrizioneAndPrezzo(String descrizione, Double prezzo);

}
