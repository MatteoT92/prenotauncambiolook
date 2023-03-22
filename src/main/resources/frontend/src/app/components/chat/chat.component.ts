import { Component, OnInit } from '@angular/core';
import { ChatWsService } from 'src/app/services/chat-ws.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  username!: string;

  constructor(public ws: ChatWsService) {
    this.username = sessionStorage.getItem('utente') as string;
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
