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

  constructor(public ws: ChatWsService) {

  }

  ngOnInit(): void {
    this.connect();
  }

  connect(): void {
    this.ws.connect();
  }

  sendMessage(): void {
    this.ws.sendMessage();
  }

}
