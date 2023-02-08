<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		</header>
		<div class="log-container">
			<form method="post" action="/login" modelAttribute="utente" class="col">
			    <span>Username</span><br>
			    <input type="text" name="username" /><br>
			    <span>Email</span><br>
			    <input type="text" name="email" /><br>
			    <span>Password</span><br>
			    <input type="password" name="password" /><br>
			    <br>
			    <input type="submit" value="Accedi"/>
			</form>
			<div class="col">
				<div class="row">
					<a href="#" class="btn" style="background-color:blue; color:white; padding:2%; margin:2%;">
			          <i class="fa fa-facebook fa-fw"></i><strong> Login con Facebook</strong>
			        </a>
			        <a href="#" class="btn" style="background-color:#007fff; color:white; padding:2%; margin:2%;">
			          <i class="fa fa-twitter fa-fw"></i><strong> Login con Twitter</strong>
			        </a>
			        <a href="#" class="btn" style="background-color:red; color:white; padding:2%; margin:2%;">
			          <i class="fa fa-google fa-fw"></i><strong> Login con Google</strong>
			        </a>
				</div>
		    </div>
		</div>
		<br><br>
		<div class="ask">
			<span>Non sei registrato? </span><a href="/sign">Registrati</a>
			<br><br>
			<span>Password dimenticata? </span><a href="/password">Recuperala</a>
		</div>
	</body>
</html>