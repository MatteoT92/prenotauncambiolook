import { Component } from '@angular/core';
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
  showPassword = false;

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
        if (this.utente.cambiaPassword) {
          this.router.navigate(['/password']);
          window.alert("Devi reimpostare la password per la tua sicurezza!");
        } else {
          this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>this.router.navigate(['/home']));
        }
      } else {
        window.alert("Credenziali non valide!");
        this.router.navigate(['/login']);
      }
    });
  }

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

}
