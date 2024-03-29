import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Utente } from '../models/utente';

@Injectable({
  providedIn: 'root'
})
export class UtenteApiService {

  private loginUrl = 'http://localhost:8081/login';
  private signUrl = 'http://localhost:8081/sign';
  private passwordUrl = 'http://localhost:8081/password';
  private disiscrizioneUrl = 'http://localhost:8081/disiscriviti';
  private utentiUrl = 'http://localhost:8081/clienti';

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
    this.router.navigateByUrl('/', {skipLocationChange: true})
    .then(()=>this.router.navigate(['/home']))
    .then(()=>window.location.reload());
  }

  modificaPassword(nuovaPassword: string) {
    return this.http.post(this.passwordUrl, {"username": sessionStorage.getItem('utente'), "password": nuovaPassword});
  }

  recuperaPassword(username: string, email: string) {
    return this.http.get<Utente>(this.passwordUrl+'?username='+username+'&email='+email);
  }

  disiscrizione() {
    return this.http.post(this.disiscrizioneUrl, {"username": sessionStorage.getItem('utente')});
  }

  listaUtenti() {
    return this.http.get<Utente[]>(this.utentiUrl);
  }

}
