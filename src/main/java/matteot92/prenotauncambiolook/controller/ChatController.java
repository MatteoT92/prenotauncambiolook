package matteot92.prenotauncambiolook.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import matteot92.prenotauncambiolook.model.entities.ChatMessage;
import matteot92.prenotauncambiolook.model.entities.MessageToSend;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes({"username"})
public class ChatController {
	
	// UTENTE CLIENTE

	/**
	 * Metodo che consente ad un utente cliente di poter inviare un messaggio
	 * sul canale topic/chat utilizzando il WebSocket
	 */
    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public MessageToSend send(ChatMessage message) throws Exception {

        MessageToSend outMessage = new MessageToSend();
        outMessage.setSender(message.getSender());
        outMessage.setText(message.getText());
        outMessage.setTime(new SimpleDateFormat("HH:mm dd-MM-yyyy").format(new Date()));
        return outMessage;
    }
    
    // UTENTE ADMIN
    
    /**
	 * Metodo che consente ad un utente admin di poter inviare un messaggio
	 * sul canale topic/chat utilizzando il WebSocket
	 */
    @MessageMapping("/admin/chat")
    @SendTo("/topic/chat")
    public MessageToSend sendAdmin(ChatMessage message) throws Exception {

        MessageToSend outMessage = new MessageToSend();
        outMessage.setSender(message.getSender());
        outMessage.setText(message.getText());
        outMessage.setTime(new SimpleDateFormat("HH:mm dd-MM-yyyy").format(new Date()));
        return outMessage;
    }
    
}