import { Injectable } from '@angular/core';

declare var SockJS: any;
declare var Stomp: any;

@Injectable({
  providedIn: 'root'
})
export class ChatWsService {

  public stompClient: any;
  messages: any[] = [];

  constructor() {
  }

  connect(): void {
    const socket = new SockJS("http://localhost:8081/chat-websocket");
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({},  (frame: any) => {
      this.stompClient.subscribe("/topic/chat", (newMessageReceived: any) => {
        this.addMessageReceived(JSON.parse(newMessageReceived.body));
      });
    });
  }

  addMessageReceived(newMessageReceived: any): void {
    this.messages.push(newMessageReceived);
  }

  sendMessage(): void {
    const sender = (document.getElementById("sender") as HTMLInputElement).value;
    const text = (document.getElementById("text") as HTMLInputElement).value;

    this.stompClient.send(
      "/chat-app/chat",
      {},
      JSON.stringify({
        sender: sender,
        text: text,
      })
    );

    (document.getElementById("text") as HTMLInputElement).value = "";
  }

}
