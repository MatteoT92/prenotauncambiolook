<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Prenota Un Cambio Look</title>
	</head>
	<body>
		<div>
			<span>Username: </span><% HttpSession hs=request.getSession();
								      String sesstionAttr=(String)session.getAttribute("username");
								      out.println(sesstionAttr);
								  	%>
		</div>
		<span><a href="/">Home</a></span>
		<span><a href="/admin/utenti">Utenti</a></span>
		<span><a href="/admin/servizi">Servizi</a></span>
		<span><a href="">Ordini</a></span>
		<span><a href="/admin/password">Password</a></span>
	</body>
</html>