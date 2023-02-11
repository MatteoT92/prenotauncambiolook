import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Utente } from 'src/app/models/utente';
import { UtenteApiService } from 'src/app/services/utente-api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  utente?: Utente;

  constructor(private api: UtenteApiService) {

  }

  ngOnInit(): void {

  }

  onSubmit(loginForm: NgForm) {
    this.api.login(loginForm.value.username, loginForm.value.email, loginForm.value.password)
    .subscribe((data) => {
      this.utente = data;
      console.log(data);
    })
  }

}
