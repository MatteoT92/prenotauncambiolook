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
