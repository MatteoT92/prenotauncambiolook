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
	</head>
	<body>
		<header>
			<h1>Prenota Un Cambio Look</h1>
			<div class="navbar2">
			   <a href="/admin/pannello"><i class="fa fa-fw fa-home"></i> Home</a> 
			   <a href="/admin/servizi"><i class="fa fa-fw fa-search"></i> Servizi</a> 
			   <a href="/admin/ordini"><i class="fa fa-fw fa-search"></i> Ordini</a>
			   <a href="/admin/clienti"><i class="fa fa-fw fa-search"></i> Clienti</a>
			   <a href="/admin/chat"><i class="fa fa-fw fa-envelope"></i> Chat</a>
			   <a href=""><i class="fa fa-fw fa-user"></i> <%
															String username = (String) session.getAttribute("username");
															out.println(username);
															%></a>
			</div>
		</header>
		<div class="log-container">
			<form method="post" action="/admin/servizi" modelAttribute="servizio" class="log">
				<h2><b>Crea nuovo servizio:</b></h2><br><br>
				<span>Descrizione</span><br>
				<input type="text" name="descrizione" /><br>
				<span>Prezzo</span><br>
			    <input type="number" name="prezzo" /><br>
			    <br>
			    <input type="submit" value="Aggiungi"/>
			</form>
		</div>
	</body>
</html>