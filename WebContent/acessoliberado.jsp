<jsp:useBean id="calcula" class="beans.BeanCursoJSP"
	type="beans.BeanCursoJSP" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:setProperty property="*" name="calcula" />
	<h3>Seja bem vindo ao sistema jsp</h3>
	<a href="salvarUsuario?acao=listartodos"><img width="100px" height="100px" src="resources/img/usuario.png" title="Cadastrar usuário"></a>
	<a href="salvarProduto?acao=listartodos"><img width="100px" height="100px" src="resources/img/produto.png" title="Cadastrar Produto"></a>
	
	
</body>
</html>