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
@Table(name = "servizi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Servizio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servizio")
	private Long id;
	@Column(nullable=false)
	private String descrizione;
	@Column(nullable=false)
	private Double prezzo;
	@OneToMany(mappedBy = "servizio",
			   fetch = FetchType.EAGER,
			   cascade = CascadeType.ALL, 
			   orphanRemoval = true)
	private Set<Ordine> ordini;

}
