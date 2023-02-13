import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UtenteApiService } from 'src/app/services/utente-api.service';

@Component({
  selector: 'app-right-menu',
  templateUrl: './right-menu.component.html',
  styleUrls: ['./right-menu.component.css']
})
export class RightMenuComponent {

  isAdmin = sessionStorage.getItem('tipo');

  constructor(private api: UtenteApiService, private router: Router) {

  }

  logout() {
    this.api.logout();
  }

  modificaPassword(nuovaPassword: string) {
    this.api.modificaPassword(nuovaPassword);
  }

}
