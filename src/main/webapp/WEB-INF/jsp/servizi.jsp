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
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	</head>
	<body>
		<header>
			<h1>Prenota Un Cambio Look</h1>
		</header>
		<jsp:useBean id="servizi" type="java.util.List" scope="session"/>
		<%
		  for(Iterator it = servizi.iterator(); it.hasNext(); ){
		    Servizio servizio = (Servizio)it.next();
		%>
		<div class="card">
		    <img src="/img/ph.png" alt="Servizio Estetico" style="width:10%">
		    <div class="container">
		    	<div>
		    		<% 
		    		String descrizione = servizio.getDescrizione(); 
		    		session.setAttribute("descrizione", descrizione);
		    		%>
			        <h4><b><% out.println(descrizione); %></b></h4>
			        <% 
			        String prezzo = servizio.getPrezzo().toString(); 
			        session.setAttribute("prezzo", prezzo);
			        %>
			        <h4>Prezzo <% out.println(prezzo); %> Euro</h4>
		        </div>
		       	<form method="post" modelAttribute="ordine" action="/ordine">
		       		<span>Data : </span><input type="date" name="data"/>
		       		<% 
		       		String data = request.getParameter("data");
		       		session.setAttribute("data", data);
		       		%>
		       		<span>Orario : </span><input type="time" name="orario"/>
		       		<% 
		       		String orario = request.getParameter("orario");
		       		session.setAttribute("orario", orario);
		       		%>
		       		<span>Quantità : </span><input type="number" name="quantita"/>
		       		<% 
		       		String quantita = request.getParameter("quantita");
		       		session.setAttribute("quantita", quantita);
		       		%>
		       		<input type="submit" value="Ordina">
		       	</form>
		    </div>
		</div>
		<%
		  }
		%>
	</body>
</html>