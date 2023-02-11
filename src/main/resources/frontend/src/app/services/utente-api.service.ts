import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Utente } from '../models/utente';

@Injectable({
  providedIn: 'root'
})
export class UtenteApiService {

  private loginUrl = 'http://localhost:8081/login';

  constructor(private http: HttpClient) {}

  login(username: string, email: string, password: string) {
    return this.http.post(this.loginUrl, {"username": username, "email": email, "password": password});
  }

}
