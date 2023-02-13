import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UtenteApiService {

  private loginUrl = 'http://localhost:8081/login';
  private signUrl = 'http://localhost:8081/sign';
  private passwordUrl = 'http://localhost:8081/password';
  private disiscrizioneUrl = 'http://localhost:8081/disiscriviti';

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, email: string, password: string) {
    return this.http.post(this.loginUrl, {"username": username, "email": email, "password": password});
  }

  sign(username: string, email: string, password: string) {
    return this.http.post(this.signUrl, {"username": username, "email": email, "password": password});
  }

  logout() {
    sessionStorage.removeItem('utente');
    sessionStorage.removeItem('tipo');
    this.router.navigate(['/login']);
  }

  modificaPassword(nuovaPassword: string) {
    return this.http.post(this.passwordUrl, {"username": sessionStorage.getItem('utente'), "password": nuovaPassword});
  }

  disiscrizione() {
    return this.http.post(this.disiscrizioneUrl, {"username": sessionStorage.getItem('utente')});
  }

}
