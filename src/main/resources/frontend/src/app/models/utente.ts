export class Utente {

  id: number;
  username: string;
  email: string;
  password: string;
  isAdmin: boolean;
  cambiaPassword: boolean;

  constructor(id: number, username: string, email: string, password: string, isAdmin: boolean, cambiaPassword: boolean) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.isAdmin = isAdmin;
    this.cambiaPassword = cambiaPassword;
  }

}
