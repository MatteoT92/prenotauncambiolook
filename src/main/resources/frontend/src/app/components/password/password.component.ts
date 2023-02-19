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

  constructor(private api: UtenteApiService, private router: Router) {

  }

  onSubmit(passwordForm: NgForm) {
    this.api.modificaPassword(passwordForm.value.password)
    .subscribe();
    this.router.navigate(['/home']);
  }

}
