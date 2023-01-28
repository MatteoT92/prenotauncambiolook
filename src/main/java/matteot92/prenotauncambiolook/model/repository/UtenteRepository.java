package matteot92.prenotauncambiolook.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import matteot92.prenotauncambiolook.model.entities.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long> {

	public List<Utente> findAll();
	public Utente save(Utente utente);
	public <Optional>Utente findByEmail(String email);
	public <Optional>Utente findByUsername(String username);
	public <Optional>Utente findByUsernameAndEmail(String username, String email);
}
