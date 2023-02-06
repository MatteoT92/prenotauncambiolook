let stompClient = null;

function connect() {
  const socket = new SockJS("/chat-websocket");

  stompClient = Stomp.over(socket);

  stompClient.connect({}, function (frame) {
    stompClient.subscribe("/topic/chat", function (newMessageReceived) {
      addMessageReceived(JSON.parse(newMessageReceived.body));
    });

    setConnected(true);
  });
}

function disconnect() {
  setConnected(false);

  if (stompClient != null) {
    stompClient.disconnect();
  }
}

function setConnected(connected) {
  if (connected) {
    document.getElementById("button-connect").style.display = "none";
    document.getElementById("button-disconnect").style.display = "block";

    document.getElementById("chat-container").style.display = "flex";

    document.getElementById("sender").disabled = true;
  } else {
    document.getElementById("button-connect").style.display = "block";
    document.getElementById("button-disconnect").style.display = "none";

    document.getElementById("chat-container").style.display = "none";

    document.getElementById("sender").disabled = false;

    document.getElementById("sender").value = "";
  }

  document.getElementById("messages-container").innerHTML = "";
}

function addMessageReceived(newMessageReceived) {
  const sender = document.getElementById("sender").value;

  const messagesContainer = document.getElementById("messages-container");

  let newMessagePosition = "left";

  if (newMessageReceived.sender == sender) {
    newMessagePosition = "right";
  }

  const newMessage = document.createElement("div");

  newMessage.className = "new-message " + newMessagePosition;

  newMessage.innerHTML = `
                    <div class="new-message-box">
                        <div class="text">${newMessageReceived.text}</div>
                        <div class="details-container">${newMessageReceived.sender} - ${newMessageReceived.time}</div>
                    </div>
                `;

  messagesContainer.appendChild(newMessage);
}

function sendMessage() {
  const sender = document.getElementById("sender").value;
  const text = document.getElementById("text").value;

  stompClient.send(
    "/chat-app/chat",
    {},
    JSON.stringify({
      sender: sender,
      text: text,
    })
  );

  document.getElementById("text").value = "";
}