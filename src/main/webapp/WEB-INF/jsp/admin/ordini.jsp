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
		<jsp:useBean id="appuntamenti" type="java.util.List" scope="request"/>
		<table>
		    <tr>
		        <th>ID Ordine</th>
		        <th>Data</th>
		        <th>Orario</th>
		        <th>Quantità</th>
		        <th>Cliente</th>
		        <th>Servizio</th>
		    </tr>
		<%
		  for(Iterator it = appuntamenti.iterator(); it.hasNext(); ){
		    Ordine ordine = (Ordine)it.next();
		%>
		    <tr>
		    	<td><%= ordine.getId() %></td>
			    <td><%= ordine.getData() %></td>
			    <td><%= ordine.getOrario() %></td>
			    <td><%= ordine.getQuantita() %></td>
			    <td><%= ordine.getUtente().getUsername() %></td>
			    <td><%= ordine.getServizio().getDescrizione() %></td>
			</tr>
		<%
		  }
		%>
		</table>
	</body>
</html>