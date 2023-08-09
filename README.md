# Prenota Un Cambio Look

Prenota Un Cambio Look è un prototipo di un'applicazione web per effettuare ordini di servizi estetici presso un ipotetico salone.

Le tecnologie utilizzate per creare questa web-app sono:

- **Spring / Spring Boot** (lato back-end);
- **MySQL** + **HeidiSQL** (lato database);
- **Angular** (lato front-end),
- **Git** (per il versionamento del codice).

Il progetto è stato realizzato come **single-page application (SPA)** col **pattern MVC**, in cui i controller presenti sono di tipo **RestController (Rest API)**.

## Prima di iniziare seguire questi passaggi:

1. Scarica ed installa [MySQL](https://dev.mysql.com/downloads/file/?id=520407 "MySQL");
2. Scarica ed installa [HeidiSQL](https://www.heidisql.com/installers/HeidiSQL_12.5.0.6677_Setup.exe "HeidiSQL") **[facoltativo]**;
3. Scarica ed installa [Git Bash](https://git-scm.com/ "Git Bash") **[facoltativo]**;
4. Una volta installato MySQL, crea un nuovo database chiamandolo "**salone**" ed esegui queste query:
    ```bash
	create table ordini (
       id_ordine bigint not null auto_increment,
        data date not null,
        is_pagato bit,
        orario time not null,
        quantita integer not null,
        id_servizio bigint,
        id_utente bigint,
        primary key (id_ordine)
    ) engine=InnoDB;

    create table pagamenti (
       id_pagamento bigint not null auto_increment,
        data date not null,
        importo float(53) not null,
        id_ordine bigint not null,
        id_utente bigint not null,
        primary key (id_pagamento)
    ) engine=InnoDB;

    create table servizi (
       id_servizio bigint not null auto_increment,
        descrizione varchar(255) not null,
        prezzo float(53) not null,
        primary key (id_servizio)
    ) engine=InnoDB;

    create table utenti (
       id_utente bigint not null auto_increment,
        cambia_password bit,
        email varchar(255) not null,
        is_admin bit not null,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id_utente)
    ) engine=InnoDB;

    alter table utenti 
       add constraint UK_9b90mk1nolf3ou90p42a93tjo unique (email);

    alter table utenti 
       add constraint UK_tn8mwk6h2wn28yyj7fco65gls unique (username);
		
	insert into utenti
		(email, is_admin, password, username)
		values ('admin@admin.it', 1, 'admin', 'Admin');
    ```
		
5. Creare il file "**application.properties**" nel path **prenotauncambiolook/src/main/resources**, e inserire le seguenti proprietà e valori: 
   ```bash
   # Tomcat
   server.port = 8081

   # MySQL
   spring.datasource.username = [Inserire nome utente]
   spring.datasource.password = [Inserire password utente]
   spring.datasource.url = jdbc:mysql://localhost:3306/salone
   spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
   spring.jpa.properties.javax.persistence.schema-generation.scripts.action = none
   #spring.jpa.properties.javax.persistence.schema-generation.scripts.action = drop-and-create
   spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target = create.sql
   spring.jpa.properties.javax.persistence.schema-generation.scripts.drop-target = drop.sql
   spring.jpa.hibernate.ddl-auto = update
   spring.jpa.show-sql = true
   spring.jpa.properties.hibernate.format_sql = true
   spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

   # Spring Email
   spring.mail.host = smtp.gmail.com
   spring.mail.port = 587
   spring.mail.username = [Inserire email account Google]
   spring.mail.password = [Inserire password (vedi istruzioni sotto per ottenerla)]
   spring.mail.properties.mail.smtp.auth = true
   spring.mail.properties.mail.smtp.starttls.enable = true
   spring.mail.properties.mail.smtp.ssl.trust = smtp.gmail.com
   ```

Per quanto riguarda le configurazioni di Spring Email si basano su provider Gmail.

Per la password, invece, bisogna abilitarne una tramite account Google per l'applicazione (https://www.youtube.com/watch?v=IWxwWFTlTUQ) e copiare questa nella proprietà **spring.email.password**.

Se si vuol utilizzare il progetto, dopo averlo scaricato dal repository, eseguire i seguenti comandi:

### Download progetto
```bash
  git clone https://github.com/MatteoT92/prenotauncambiolook.git
```

### Avvio del progetto Spring Boot e relativo back-end
```bash
cd prenotauncambiolook

mvnw spring-boot:run
```

### Scaricamento delle dipendenze richieste e avvio del front-end con Angular
```bash
cd prenotauncambiolook/src/main/resources/frontend

npm install

npm start
```

Una volta eseguiti questi comandi dirigersi sul browser e andare sull'indirizzo:
```http
http://localhost:4200/
```

## Autore:
Matteo Tartaglione
## 🔗 Links
[![github](https://img.shields.io/github/followers/MatteoT92?style=for-the-badge&logo=github&logoColor=white)](https://github.com/MatteoT92)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/matteotartaglione/)
