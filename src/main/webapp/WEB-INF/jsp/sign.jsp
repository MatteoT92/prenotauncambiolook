<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Prenota Un Cambio Look</title>
	</head>
	<body>
		<form method="post" action="/sign" modelAttribute="utente">
		    <span>Username : </span><br>
		    <input type="text" name="username" /><br>
		    <span>Email : </span><br>
		    <input type="text" name="email" /><br>
		    <span>Password : </span><br>
		    <input type="password" name="password" /><br>
		    <br>
		    <input type="submit" />
		</form>
	</body>
</html>