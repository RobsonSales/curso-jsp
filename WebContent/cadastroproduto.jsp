<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<title>Cadastro de Produto</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
<table>
		<tr>
			<td><a href="acessoliberado.jsp"><img
					src="resources/img/voltar.png" width="50px" height="50px"
					title="Voltar a home"></a></td>
			<td><a href="index.jsp"><img src="resources/img/sair.png"
					width="50px" height="50px" title="Sair do sistema"></a></td>
	</table>
	<center>
		<h1>Cadastro de Produto</h1>
		<h3 style="color: red">${msg}</h3>

	</center>
	<form action="salvarProduto" method="post" id="formProduto"
		onsubmit="return validarCampos() ? true : false">

		<ul class="form-style-1">
			<li>
				<table>

					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${produto.id}" />
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${produto.nome}" />
					</tr>
					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${produto.quantidade}" />
					</tr>
					<tr>
						<td>Valor R$:</td>
						<td><input type="text" id="valor" name="valor"
							value="${produto.valor}" />
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="btn btn-success"
							value="Salvar"> <input type="submit"
							class="btn btn-danger" value="Cancelar"
							onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'">
						</td>
					</tr>

				</table>
			</li>
		</ul>
	</form>
	<div class="container">
		<table class="table">
			<div class="jumbotron text-center">
				<h1>Lista de produtos cadastrados</h1>

			</div>
			<thead class="thead-dark">
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th>Quantidade</th>
					<th>Valor R$</th>
					<th>Excluir</th>
					<th>Editar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${produtos}" var="prod">
					<tr>
						<td style="width: 150px"><c:out value="${prod.id}"></c:out></td>
						<td style="width: 150px"><c:out value="${prod.nome}"></c:out></td>
						<td><c:out value="${prod.quantidade}"></c:out></td>
						<td><c:out value="${prod.valor}"></c:out></td>

						<td><a href="salvarProduto?acao=delete&produto=${prod.id}"><button
									type="button" class="btn btn-danger" alt="Excluir"
									title="Excluir Produto">Excluir</button></a></td>
						<td><a href="salvarProduto?acao=editar&produto=${prod.id}"><button
									type="button" class="btn btn-warning" alt="Editar"
									title="Editar Produto">Editar</button></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
	function validarCampos() {
		if (document.getElementById("nome").value == '') {
			alert('Informe o nome do produto');
			return false;
			
		} else if (document.getElementById("quantidade").value == '') {
			alert('Informe a quantidade do produto');
			return false;
			
		} else if (document.getElementById("valor").value == '') {
			alert('Informe o valor do produto');
			return false;
			
		}
		return true;
	}
	</script>

</body>
</html>