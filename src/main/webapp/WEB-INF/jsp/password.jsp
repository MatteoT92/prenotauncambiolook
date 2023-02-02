<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Prenota Un Cambio Look</title>
		<link rel="stylesheet" href="/css/style.css">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	</head>
	<body>
		<header>
			<h1>Prenota Un Cambio Look</h1>
		</header>
		<div class="log-container">
			<form method="post" action="/home" modelAttribute="utente" class="log">
			    <span>Username : </span><br>
			    <input type="text" name="username" /><br>
			    <span>Email : </span><br>
			    <input type="text" name="email" /><br>
			    <span>Nuova Password : </span><br>
			    <input type="password" name="password" /><br>
			    <br>
			    <input type="submit" value="Conferma"/>
			</form>
		</div>
	</body>
</html>