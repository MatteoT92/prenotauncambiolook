package matteot92.prenotauncambiolook.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MessageToSend {

    private String sender;
    private String text;
    private String time;
    
}