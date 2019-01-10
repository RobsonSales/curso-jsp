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

<title>Cadastro de Telefone</title>
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
		<h1>Cadastro de Telefone</h1>
		<h3 style="color: red">${msg}</h3>
	</center>
	<br/>
	<form action="salvarTelefone" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${userEscolhido.id}"/>
						
						<td>Usuário:</td>
						<td><input type="text" readonly="readonly" id="nome" name="nome" value="${userEscolhido.nome}" />
					</tr>
					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero" />
						
						<td>Tipo:</td>
						<td>
							<select id="tipo" name="tipo">
								<option>Casa</option>
								<option>Contato</option>
								<option>Celular</option>
							</select>
						</td>
					<tr>
						<td></td>
						<td><input type="submit" 
							value="Salvar"> 
						</td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>Código</th>
					<th>Usuário</th>
					<th>Número</th>
					<th>Tipo</th>
					<th>Excluir</th>
				</tr>
			</thead>
			<tbody>
				<div class="jumbotron text-center">
					<h1>Lista de telefones cadastrados</h1>

				</div>
				<c:forEach items="${telefone}" var="fone">
					<tr>
						<td style="width: 150px"><c:out value="${fone.id}"></c:out></td>
						<td style="width: 150px"><c:out value="${fone.usuario}"></c:out></td>
						<td><c:out value="${fone.numero}"></c:out></td>
						<td><c:out value="${fone.tipo}"></c:out></td>

						<td><a href="salvarTelefone?acao=delete&foneId=${fone.id}"><button
									type="button" class="btn btn-danger" alt="Excluir"
									title="Excluir usuário">Excluir</button></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numemro").value == '') {
				alert('Informe o número');
				return false;

			} else if (document.getElementById("tipo").value == '') {
				alert('Informe a tipo');
				return false;

			} 
			
			return true;
		}

	</script>

</body>
</html>