import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLogged!: boolean;

  constructor() {
    this.isLogged = (sessionStorage.getItem('utente') != null && sessionStorage.getItem('utente') != undefined);
  }

}
