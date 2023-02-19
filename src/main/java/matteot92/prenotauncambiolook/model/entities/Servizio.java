package matteot92.prenotauncambiolook.model.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@JsonPropertyOrder({ "id", "descrizione", "prezzo"})
public class Servizio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servizio")
	private Long id;
	@Column(nullable=false)
	private String descrizione;
	@Column(nullable=false)
	private Double prezzo;
	
}
