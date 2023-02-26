# Prenota Un Cambio Look

Progetto Web-App per prenotazione servizi estetici

Questo è un prototipo di un'applicazione web per effettuare ordini di servizi estetici presso un salone.

Trattasi di un progetto sviluppato durante la fase di stage presso la società [**Exprivia S.p.A.**](https://www.exprivia.it/it/ "Exprivia S.p.A.") .

Le tecnologie utilizzate per creare questa web-app sono:

- **Spring** (lato back-end);
- **Microsoft SQL Server 2019 Express** + **SQL Server Management Studio 18.12.1** (lato database);
- **JSP** (branch "master") / **Angular** (branch "second") (lato front-end).

## Prima di iniziare seguire questi passaggi:

1. Scarica ed installa [Microsoft SQL Server 2019 Express](https://www.microsoft.com/it-it/download/details.aspx?id=101064 "Microsoft SQL Server 2019 Express");
2. Scarica ed installa [SQL Server Management Studio 18.12.1](https://go.microsoft.com/fwlink/?linkid=2199013&clcid=0x410 "SQL Server Management Studio 18.12.1");
3. Una volta installato SQL Server Management Studio, crea un nuovo database chiamandolo "**salone**" ed esegui queste query:

		create table ordini (
		id_ordine bigint identity not null,
		data date not null,
		is_pagato bit,
		orario time not null,
		quantita int not null,
		id_servizio bigint,
		id_utente bigint,
		primary key (id_ordine)
		);
		
		create table pagamenti (
		id_pagamento bigint identity not null,
		data date not null,
		importo float(53) not null,
		id_ordine bigint not null,
		id_utente bigint not null,
		primary key (id_pagamento)
		);
		
		create table servizi (
		id_servizio bigint identity not null,
		descrizione varchar(255) not null,
		prezzo float(53) not null,
		primary key (id_servizio)
		);
		
		create table utenti (
		id_utente bigint identity not null,
		cambia_password bit,
		email varchar(255) not null,
		is_admin bit not null,
		password varchar(255) not null,
		username varchar(255) not null,
		primary key (id_utente)
		);
		
		alter table utenti
		add constraint UK_9b90mk1nolf3ou90p42a93tjo unique (email);
		
		alter table utenti
		add constraint UK_tn8mwk6h2wn28yyj7fco65gls unique (username);
		
		insert into utenti
		(email
		,is_admin
		,password
		,username)
		values
		('admin@admin.it'
		,1
		,'admin'
		,'Admin');
		
4. Aprire il file "**application.properties**", presente al path **prenotauncambiolook/src/main/resources**, e modificare i valori delle seguenti proprietà: 

   1) ***Microsoft SQL Server*:**
   **spring.datasource.username** = *Inserisci il tuo username*
   **spring.datasource.password** = *Inserisci la tua password*
   Per quanto riguarda **spring.datasource.url**, se si vuol lasciare come di default già presente, seguire questi passaggi:
   - vai al path ***C:\Windows\SysWOW64\SQLServerManager15.msc*** ed esegui il file; 
   - clicca sulla sinistra su ***"Configurazione di rete SQL Server"* **e sulla destra dovrebbe apparire ***"Protocolli per SQLEXPRESS"***, poi fai tasto destro su ***"TCP/IP"*** e clicca su ***"Abilita"***; 
   - sempre su ***"TCP/IP"*** fai doppio click e vai sulla scheda ***"Indirizzi IP"***; 
   - scorri la lista fino a trovare la scritta ***"IPAll"*** e imposta per il campo ***"Porta TCP"*** il numero ***1434* **e per il campo ***"Porte dinamiche TCP"* ** ***lascia vuoto*** (qualora vi fosse inserito qualcosa), infine clicca su ***Ok***; 
   - vai sulla sinistra e fai doppio click su ***"Servizi di SQL Server"*** e fai tasto destro sulla voce ***"SQL Server (SQLEXPRESS)"*** e infine clicca ***"Riavvia"***.
   
   2) ***Spring Email***:
   **spring.mail.username** = *Inserisci la tua email*
   Per quanto riguarda le configurazioni si basano su provider Gmail.
   Per la password, invece, bisogna abilitarne una tramite account Google per l'applicazione (https://www.youtube.com/watch?v=IWxwWFTlTUQ) e copiare questa nella proprietà **spring.email.password**.

Il progetto inizialmente era stato pensato come un MVC avente la parte view gestita tramite JSP.
Successivamente si è deciso, dopo averne iniziato un percorso di studio, di effettuare il porting dello stesso su Angular per la parte view e di trasformare i controller presenti nell'applicazione Spring in RestController.

Se si vuol utilizzare il progetto con uno stato più avanzato dal punto di vista delle funzionalità si consiglia di scaricare dal repository il branch "second" e da terminale eseguire i seguenti comandi:

##### per avviare il progetto Spring Boot e relativo back-end
cd prenotauncambiolook
mvnw spring-boot:run
##### per avviare il front-end con Angular
cd prenotauncambiolook/src/main/resources/frontend
npm install
npm start

Una volta eseguiti questi comandi dirigersi sul browser e andare sull'indirizzo **http://localhost:4200/** .
