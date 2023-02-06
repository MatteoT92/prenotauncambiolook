<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
		<title>Prenota Un Cambio Look</title>
		<link rel="stylesheet" href="/css/style.css">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js" integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="/js/script.js"></script>
    </head>
    <body onload="disconnect()">
        <header>
			<h1>Prenota Un Cambio Look</h1>
			<div class="navbar">
			   <a href="/home"><i class="fa fa-fw fa-home"></i> Home</a> 
			   <a href="/servizi"><i class="fa fa-fw fa-search"></i> Servizi</a> 
			   <a href="/chat"><i class="fa fa-fw fa-envelope"></i> Contattaci</a>
			   <a href=""><i class="fa fa-fw fa-user"></i> <%
															String username = (String) session.getAttribute("username");
															out.println(username);
															%></a>
			</div>
		</header>
        <div class="container2">
            <div class="connect-container">
                <div class="input-container">
                    <input type="text" id="sender" />
                </div>
                <div>
                    <button id="button-connect" onclick="connect();">
                        CONNETTI
                    </button>
                    <button id="button-disconnect" onclick="disconnect();">
                        DISCONNETTI
                    </button>
                </div>               	
            </div>
            <div id="chat-container">
                <div id="messages-container"></div>
                <div class="send-message-container">
                    <div class="input-container">
                        <input type="text" id="text" placeholder="Scrivi un messaggio"/>
                    </div>
                    <div>
                        <button id="button-sendmessage" onclick="sendMessage();">INVIA</button>            		
                    </div>            		
                </div>                
            </div>
        </div>
    </body>
</html>