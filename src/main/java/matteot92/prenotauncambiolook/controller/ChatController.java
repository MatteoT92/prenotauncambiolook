package matteot92.prenotauncambiolook.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import matteot92.prenotauncambiolook.model.entities.ChatMessage;
import matteot92.prenotauncambiolook.model.entities.MessageToSend;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

	/**
	 * Metodo che consente ad un utente cliente di poter inviare un messaggio
	 * sul canale topic/chat utilizzando il WebSocket
	 */
    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    @CrossOrigin(origins = "http://localhost:4200/chat")
    public MessageToSend send(ChatMessage message) throws Exception {

        MessageToSend outMessage = new MessageToSend();
        outMessage.setSender(message.getSender());
        outMessage.setText(message.getText());
        outMessage.setTime(new SimpleDateFormat("HH:mm dd-MM-yyyy").format(new Date()));
        return outMessage;
    }

}