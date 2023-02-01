<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Prenota Un Cambio Look</title>
	</head>
	<body>
		<label><b>Crea nuovo servizio:</b></label><br><br>
		<form method="post" action="/admin/servizi", modelAttribute="servizio">
			<span>Codice : </span><br>
			<input type="text" name="id" /><br>
			<span>Descrizione : </span><br>
			<input type="text" name="descrizione" /><br>
			<span>Prezzo : </span><br>
		    <input type="number" name="prezzo" /><br>
		    <br>
		    <input type="submit" value="Aggiungi"/>
		</form>		
	</body>
</html>