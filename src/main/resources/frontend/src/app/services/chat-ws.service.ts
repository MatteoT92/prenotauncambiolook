import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

@Injectable({
  providedIn: 'root'
})
export class ChatWsService {
/*
  public stompClient: any;
  public msg = Array();

  constructor() {
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8081/chat-websocket';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect({}, function(frame: any) {
      that.stompClient.subscribe('/topic/chat', (message: any) => {
        if (message.body) {
          that.msg.push(message.body);
        }
      });
    });
  }

  sendMessage(message: any) {
    this.stompClient.send('/chat-app/chat' , {}, message);
  }
*/

  public stompClient: any;

  constructor() {
    this.connect();
  }

  connect(): void {
    /*
    Crea un WebSocket sull'endpoint chat-websocket,
    gestito dal metodo registerStompEndpoints della classe java WebSocketConfiguration,
    con cui comunicheranno il client col server
    */
    const socket = new SockJS("http://localhost:8081/chat-websocket");

    this.stompClient = Stomp.over(socket);

    /*
    Il client creato si connette e resta in ascolto sul canale
    di sottoscrizione topic/chat, sul quale arriveranno i messaggi
    */
    this.stompClient.connect({},  (frame: any) => {
      this.stompClient.subscribe("http://localhost:8081/topic/chat",  (newMessageReceived: any) => {
        this.addMessageReceived(JSON.parse(newMessageReceived.body));
      });
      this.setConnected(true);
    });
  }

  disconnect(): void {
    this.setConnected(false);

    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
  }

  setConnected(connected: boolean): void {
    if (connected) {
      document.getElementById("button-connect")!.style.display = "none";
      document.getElementById("button-disconnect")!.style.display = "block";

      document.getElementById("chat-container")!.style.display = "flex";

      (document.getElementById("sender") as HTMLInputElement).disabled = true;
    } else {
      document.getElementById("button-connect")!.style.display = "block";
      document.getElementById("button-disconnect")!.style.display = "none";

      document.getElementById("chat-container")!.style.display = "none";

      (document.getElementById("sender") as HTMLInputElement).disabled = false;
    }

    document.getElementById("messages-container")!.innerHTML = "";
  }

  addMessageReceived(newMessageReceived: any): void {
    const sender = (document.getElementById("sender") as HTMLInputElement).value;

    const messagesContainer = document.getElementById("messages-container");

    let newMessagePosition = "left";

    if (newMessageReceived.sender == sender) {
      newMessagePosition = "right";
    }

    const newMessage = document.createElement("div");

    newMessage.className = "new-message " + newMessagePosition;

    newMessage.innerHTML = `
      <div class="new-message-box">
        <div class="text">${newMessageReceived.text}</div>
        <div class="details-container">${newMessageReceived.sender} - ${newMessageReceived.time}</div>
      </div>
    `;

    messagesContainer!.appendChild(newMessage);
  }

  sendMessage(): void {
    const sender = (document.getElementById("sender") as HTMLInputElement).value;
    const text = (document.getElementById("text") as HTMLInputElement).value;

    this.stompClient.send(
      "http://localhost:8081/chat-app/chat",
      {},
      JSON.stringify({
        sender: sender,
        text: text,
      })
    );

    (document.getElementById("text") as HTMLInputElement).value = "";
  }

}
