<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Prenota Un Cambio Look</title>
		<link rel="stylesheet" href="/css/style.css">
	</head>
	<body>
		<header>
			<h1>Prenota Un Cambio Look</h1>
			<div class="navbar">
			   <a class="active" href="#"><i class="fa fa-fw fa-home"></i> Home</a> 
			   <a href="/servizi"><i class="fa fa-fw fa-search"></i> Servizi</a> 
			   <a href=""><i class="fa fa-fw fa-envelope"></i> Contattaci</a>
			</div>
		</header>
		<div id="user">
			<span>Profilo</span><br>
			<br>
			<div>
				<span>Username: </span>
					<%
					String username = (String) session.getAttribute("username");
					out.println(username);
					%>
			</div>
			<a href="/ordini">I miei ordini</a><br>
			<a href="/password">Modifica Password</a><br>
			<a href="/cancellati">Cancellati</a><br>
			<a href="/logout">Logout</a><br>
		</div>
	</body>
</html>