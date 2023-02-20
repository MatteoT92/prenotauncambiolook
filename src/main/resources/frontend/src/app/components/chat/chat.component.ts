import { AfterViewInit, Component, OnInit } from '@angular/core';

declare function connect(): void;
declare function disconnect(): void;
declare function setConnected(connected: any): void;
declare function addMessageReceived(newMessageReceived: any): void;
declare function sendMessage(): void;

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, AfterViewInit {

  username = sessionStorage.getItem('utente');
  scriptElement!: HTMLScriptElement;

  constructor() {}

  ngAfterViewInit(): void {
    console.log('ngAfterViewInit');
    this.scriptElement = document.createElement("script");
    this.scriptElement.src = "/assets/js/script.js";
    document.body.appendChild(this.scriptElement);
  }

  ngOnInit(): void {

  }

}
