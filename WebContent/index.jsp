<jsp:useBean id="calcula" class="beans.BeanCursoJSP"
	type="beans.BeanCursoJSP" scope="page" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

	<div class="login-page">
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">

				Login: <input type="text" id="login" name="login"> <br />
				Senha: <input type="password" id="senha" name="senha"> <br />
				<button type="submit" value="logar">Logar</button>
			</form>
		</div>
	</div>
</body>
</html>