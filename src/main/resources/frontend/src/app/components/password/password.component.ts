import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UtenteApiService } from 'src/app/services/utente-api.service';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent {

  isAdmin = sessionStorage.getItem('tipo');
  password!: string;

  constructor(private api: UtenteApiService, private router: Router) {

  }

  onSubmit(passwordForm: NgForm) {
    this.api.modificaPassword(passwordForm.value.password)
    .subscribe();
    this.router.navigate(['/home']);
  }

  onSubmitRecupera(recuperaPasswordForm: NgForm) {
    let randomPassword = Math.random().toString(36).slice(-8); // genera una password casuale per poter accedere
    this.api.recuperaPassword(recuperaPasswordForm.value.username, recuperaPasswordForm.value.email)
    .subscribe(
      (data) => {
        if (data != null || data != undefined) {
          sessionStorage.setItem('utente', recuperaPasswordForm.value.username);
          this.api.modificaPassword(randomPassword)
          .subscribe(
            (data) => {
              this.password = randomPassword;
            }
          );
        }
      }
    );
  }

}
