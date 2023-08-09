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
  showPassword = false;
  showConfirmPassword = false;

  constructor(private api: UtenteApiService, private router: Router) {

  }

  onSubmit(passwordForm: NgForm) {
    if (passwordForm.value.password === passwordForm.value.confermaPassword) {
      this.api.modificaPassword(passwordForm.value.password)
      .subscribe((data) => {
        window.alert("Password modificata con successo");
        this.router.navigate(['/home']);
      });
    } else {
      window.alert("Le password digitate non corrispondono");
    }
  }

  onSubmitRecupera(recuperaPasswordForm: NgForm) {
    this.api.recuperaPassword(recuperaPasswordForm.value.username, recuperaPasswordForm.value.email)
    .subscribe((data) => {
      window.alert("Password di ripristino inviata al tuo indirizzo email");
      this.router.navigate(['/login']);
    });
  }

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  public toggleConfirmPasswordVisibility(): void {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

}
