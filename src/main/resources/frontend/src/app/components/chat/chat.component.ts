import { Component, OnInit } from '@angular/core';

declare var connect: any;
declare var disconnect: any;
declare var setConnected: any;
declare var addMessageReceived: any;
declare var sendMessage: any;

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  username = sessionStorage.getItem('utente');

  constructor() { }

  ngOnInit(): void {

  }

}
