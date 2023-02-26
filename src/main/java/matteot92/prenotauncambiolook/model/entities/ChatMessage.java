package matteot92.prenotauncambiolook.model.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonPropertyOrder({ "sender", "text"})
public class ChatMessage {

	private String sender;
    private String text;
	
}
