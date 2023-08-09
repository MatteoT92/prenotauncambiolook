package matteot92.prenotauncambiolook.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import matteot92.prenotauncambiolook.model.entities.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long> {

	public Optional<Utente> findByEmail(String email);
	public Optional<Utente> findByUsername(String username);
	public Optional<Utente> findByUsernameAndEmail(String username, String email);
	public Optional<Utente> findByUsernameAndEmailAndPassword(String username, String email, String password);
	@Query("SELECT utente.password FROM Utente utente WHERE utente.username = ?1 AND utente.email = ?2")
	public String getPasswordByUsernameAndEmail(String username, String email);
	
}
