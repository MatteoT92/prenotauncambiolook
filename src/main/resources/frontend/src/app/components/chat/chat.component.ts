import { Component, OnInit } from '@angular/core';
import { ChatWsService } from 'src/app/services/chat-ws.service';
declare var SockJS: any;
declare var Stomp: any;

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  username = sessionStorage.getItem('utente');
  input: any;

  /*

  constructor(public ws: ChatWsService) {
  }

  ngOnInit(): void {
    this.ws.connect();
  }

  connect() {
    this.ws.connect();
  }

  disconnect() {
    this.ws.disconnect();
  }

  sendMessage() {
    this.ws.sendMessage();
  }
  */

  private stompClient: any;

  messages: any[] = [];

  constructor() {}

  ngOnInit(): void {
    this.connect();
    console.log(this.messages);
  }

  connect(): void {
    const socket = new SockJS("http://localhost:8081/chat-websocket");
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, (frame: any) => {
      this.stompClient.subscribe('/topic/chat', (message: { body: string; }) => {
        const newMessage = JSON.parse(message.body);
        this.messages.push(newMessage);
      });
    });
  }

  sendMessage(): void {
    const sender = (document.getElementById("sender") as HTMLInputElement).value;
    const text = (document.getElementById("text") as HTMLInputElement).value;

    this.stompClient.send(
      "/app/chat",
      {},
      JSON.stringify({
        sender: sender,
        text: text,
      })
    );
    this.messages.push({ sender: sender, text: text });

    (document.getElementById("text") as HTMLInputElement).value = "";
  }
}
