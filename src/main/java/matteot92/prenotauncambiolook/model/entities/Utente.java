package matteot92.prenotauncambiolook.model.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utente")
	private Long id;
	@Column(nullable=false,
			unique=true)
	private String username;
	@Column(nullable=false,
			unique=true)
	private String email;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private Boolean isAdmin;
	@OneToMany(mappedBy = "utente",
			   fetch = FetchType.EAGER,
			   cascade = CascadeType.ALL,
			   orphanRemoval = true)
	private Set<Ordine> ordini;

}
