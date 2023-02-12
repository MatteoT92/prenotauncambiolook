import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Utente } from 'src/app/models/utente';
import { UtenteApiService } from 'src/app/services/utente-api.service';

@Component({
  selector: 'app-sign',
  templateUrl: './sign.component.html',
  styleUrls: ['./sign.component.css']
})
export class SignComponent {

  utente?: Utente;

  constructor(private api: UtenteApiService, private router: Router) {

  }

  onSubmit(signForm: NgForm) {
    this.api.sign(signForm.value.username, signForm.value.email, signForm.value.password)
    .subscribe((data: any) => {
      this.utente = data;
      console.log(this.utente);
      this.router.navigate(['/login']);
    })
  }
}
