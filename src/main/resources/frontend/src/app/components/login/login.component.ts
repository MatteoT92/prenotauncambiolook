import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Utente } from 'src/app/models/utente';
import { UtenteApiService } from 'src/app/services/utente-api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  utente?: Utente;

  constructor(private api: UtenteApiService, private router: Router) {

  }

  onSubmit(loginForm: NgForm) {
    this.api.login(loginForm.value.username, loginForm.value.email, loginForm.value.password)
    .subscribe((data: any) => {
      this.utente = data;
      if (this.utente != null || this.utente != undefined) {
        sessionStorage.setItem('utente', this.utente.username);
        if (this.utente.isAdmin) {
          sessionStorage.setItem('tipo', 'admin');
        } else {
          sessionStorage.setItem('tipo', 'cliente');
        }
        this.router.navigate(['/home']);
      }
    })
  }

}
