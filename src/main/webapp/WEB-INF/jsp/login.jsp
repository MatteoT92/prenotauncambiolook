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
		</header>
		<br><br>
		<div class="log-container">
			<form method="post" action="/login", modelAttribute="utente" class="log">
			    <span>Username</span><br>
			    <input type="text" name="username" /><br>
			    <span>Email</span><br>
			    <input type="text" name="email" /><br>
			    <span>Password</span><br>
			    <input type="password" name="password" /><br>
			    <br>
			    <input type="submit" />
			</form>
		</div>
		<br><br>
		<span>Non sei registrato? </span><a href="/sign">Registrati</a>
		<br><br>
		<span>Password dimenticata? </span><a href="/password">Recuperala</a>
	</body>
</html>