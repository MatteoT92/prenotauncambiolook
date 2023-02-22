import { Component, OnInit } from '@angular/core';
import { ChatWsService } from 'src/app/services/chat-ws.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  username = sessionStorage.getItem('utente');
  input: any;

  constructor(public ws: ChatWsService) {
  }

  ngOnInit(): void {

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

}
