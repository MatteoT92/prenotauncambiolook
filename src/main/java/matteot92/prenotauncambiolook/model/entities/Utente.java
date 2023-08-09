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
@Table(name = "utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonPropertyOrder({ "id", "username", "email", "password", "isAdmin", "cambiaPassword"})
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
	private Boolean cambiaPassword;

}
