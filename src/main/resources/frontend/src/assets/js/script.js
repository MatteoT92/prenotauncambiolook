let stompClient = null;

// Crea una connessione WebSocket ad un client
function connect() {
  /*
  Crea un WebSocket sull'endpoint chat-websocket, 
  gestito dal metodo registerStompEndpoints della classe java WebSocketConfiguration,
  con cui comunicheranno il client col server
  */
  const socket = new SockJS("/chat-websocket");

  stompClient = Stomp.over(socket);

  /*
  Il client creato si connette e resta in ascolto sul canale
  di sottoscrizione topic/chat, sul quale arriveranno i messaggi
  */
  stompClient.connect({}, function (frame) {
    stompClient.subscribe("/topic/chat", function (newMessageReceived) { // verifica la presenza di messaggi
      addMessageReceived(JSON.parse(newMessageReceived.body));			 // e li mostra nel body HTML
    });																	 // dopo averlo formattato

    setConnected(true); // imposta lo stato connessione su true
  });
}

// Effettua il logout dal client
function disconnect() {
  setConnected(false); // imposta lo stato connessione su false

  if (stompClient != null) {  // verifica che l'oggetto WebSocket non sia nullo
    stompClient.disconnect(); // e effettua il logout
  }
}

/*
Modifica il comportamento degli elementi cercati tramite id
in base se il parametro connected della funzione è true o false
*/
function setConnected(connected) {
  if (connected) { // true
    document.getElementById("button-connect").style.display = "none";
    document.getElementById("button-disconnect").style.display = "block";

    document.getElementById("chat-container").style.display = "flex";

    document.getElementById("sender").disabled = true;
  } else { // false
    document.getElementById("button-connect").style.display = "block";
    document.getElementById("button-disconnect").style.display = "none";

    document.getElementById("chat-container").style.display = "none";

    document.getElementById("sender").disabled = false;
  }

  document.getElementById("messages-container").innerHTML = ""; // crea un campo messaggi vuoto nel div con quell'id
}																// in cui inseriremo il messaggio da inviare

// Aggiunge al box dei messaggi il messaggio appena inviato/ricevuto
function addMessageReceived(newMessageReceived) {
  const sender = document.getElementById("sender").value; // recupera il nome di chi ha inviato il messaggio

  const messagesContainer = document.getElementById("messages-container"); // recupera il testo del messaggio

  /*
  Effettua la verifica del nome di chi ha inviato il messaggio
  e lo posizione a sinistra se è l'admin
  oppure a destra se siamo noi
  */
  let newMessagePosition = "left";

  if (newMessageReceived.sender == sender) {
    newMessagePosition = "right";
  }

  /*
  Crea un div container in cui verrà inserito il messaggio ricevuto
  e lo aggiunge al box dei messaggi della chat
  */
  const newMessage = document.createElement("div"); // crea div

  newMessage.className = "new-message " + newMessagePosition; // crea dinamicamente il nome della classe con la posizione

  newMessage.innerHTML = `
                    <div class="new-message-box">
                        <div class="text">${newMessageReceived.text}</div>
                        <div class="details-container">${newMessageReceived.sender} - ${newMessageReceived.time}</div>
                    </div>
                `; // crea dinamicamente il div col messaggio da aggiungere

  messagesContainer.appendChild(newMessage); // aggiunge il messaggio al box della chat
}

// Invia il messaggio al server
function sendMessage() {
  const sender = document.getElementById("sender").value; // recupera il nome di chi invia il messaggio
  const text = document.getElementById("text").value;	  // recupera il testo del messaggio

  stompClient.send(    // il client invia al server sull'endpoint chat-app/chat,
    "/chat-app/chat",  // gestito dal metodo configureMessageBroker della classe java WebSocketConfiguration,
    {},				   // il messaggio formattato
    JSON.stringify({
      sender: sender,
      text: text,
    })
  );

  document.getElementById("text").value = ""; // imposta un campo iniziale vuoto per il messaggio
}