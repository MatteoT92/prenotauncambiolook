<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="matteot92.prenotauncambiolook.model.entities.Servizio" %>
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
		<jsp:useBean id="servizi" type="java.util.List" scope="request"/>
		<%
		  for(Iterator it = servizi.iterator(); it.hasNext(); ){
		    Servizio servizio = (Servizio)it.next();
		%>
		<div class="card">
		    <img src="img_avatar.png" alt="Avatar" style="width:100%">
		    <div class="container">
		        <h4><b><%= servizio.getDescrizione() %></b></h4>
		        <p>Prezzo <%= servizio.getPrezzo().toString() %> Euro</p>
		    </div>
		</div>
		<%
		  }
		%>
	</body>
</html>