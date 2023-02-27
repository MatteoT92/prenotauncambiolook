import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLogged: boolean = (sessionStorage.getItem('utente') != null && sessionStorage.getItem('utente') != undefined);

  constructor() {
    
  }

}
