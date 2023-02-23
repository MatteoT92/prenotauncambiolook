import { AfterContentChecked, AfterContentInit, AfterViewChecked, AfterViewInit, Component, OnInit } from '@angular/core';
import { ChatWsService } from 'src/app/services/chat-ws.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, AfterContentInit, AfterViewInit, AfterContentChecked, AfterViewChecked {

  username = sessionStorage.getItem('utente');
  input: any;

  constructor(public ws: ChatWsService) {
  }

  ngOnInit(): void {
    this.ws.connect();
  }

  ngAfterContentInit(): void {
    //this.ws.connect()
  }
  ngAfterViewInit(): void {
    this.ws.connect()
  }
  ngAfterContentChecked(): void {
    //this.ws.connect()
  }
  ngAfterViewChecked(): void {
    //this.ws.connect()
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
