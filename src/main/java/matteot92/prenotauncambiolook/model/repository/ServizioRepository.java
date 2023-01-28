package matteot92.prenotauncambiolook.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import matteot92.prenotauncambiolook.model.entities.Servizio;

@Repository
public interface ServizioRepository extends CrudRepository<Servizio, String> {

	public List<Servizio> findAll();
	public Servizio save(Servizio servizio);
}
