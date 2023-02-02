<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="matteot92.prenotauncambiolook.model.entities.Ordine" %>
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
		<jsp:useBean id="ordini" type="java.util.Set" scope="request"/>
		<%
		  for(Iterator it = ordini.iterator(); it.hasNext(); ){
		    Ordine ordine = (Ordine)it.next();
		%>
		<table>
		    <tr>
		        <th>ID Ordine</th>
		        <th>Data</th>
		        <th>Orario</th>
		        <th>Quantità</th>
		        <th>Cliente</th>
		        <th>Servizio</th>
		    </tr>
		    <tr>
		    	<td><%= ordine.getId() %></td>
			    <td><%= ordine.getData() %></td>
			    <td><%= ordine.getOrario() %></td>
			    <td><%= ordine.getQuantita() %></td>
			    <td><%= ordine.getUtente().getUsername() %></td>
			    <td><%= ordine.getServizio().getDescrizione() %></td>
			</tr>
		</table>
		<%
		  }
		%>
	</body>
</html>