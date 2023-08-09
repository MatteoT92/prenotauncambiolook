import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
    username = sessionStorage.getItem('utente');
    isAdmin = sessionStorage.getItem('tipo');
    showUserMenu = false;

    toggleUserMenu() {
      this.showUserMenu = !this.showUserMenu;
    }
}
