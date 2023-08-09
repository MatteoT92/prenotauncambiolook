
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
