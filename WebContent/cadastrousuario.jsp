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

<title>Cadastro de Usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>

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
		<h1>Cadastro de usuário</h1>
		<h3 style="color: red">${msg}</h3>
		<h3 style="color: red">${msg_senha}</h3>
	</center>
	<br/>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false">
		<ul class="form-style-1">
			<li>
				<table>

					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" />
						<td>CEP:</td>
						<td><input type="text" id="cep" name="cep" placeholder="Informe o CEP válido"
							value="${user.cep}" />
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" placeholder="Informe o login do usuário"/>
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua" placeholder="Informe a rua do usuário"
							value="${user.rua}" />
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha" placeholder="Informe s senha do usuário"
							value="${user.senha}" />
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro" placeholder="Informe o bairro do usuário"
							value="${user.bairro}" />
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" placeholder="Informe o nome do usuário"
							value="${user.nome}" />
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade" placeholder="Informe a cidade	 do usuário"
							value="${user.cidade}" />
					</tr>

					<tr>
						<td>Telefone:</td>
						<td><input type="text" id="tel" name="tel"
							placeholder="(99)99999-9999" value="${user.tel}" />
						<td>Estado:</td>
						<td><input type="text" id="uf" name="uf" value="${user.uf}" />
					</tr>

					<tr>
						<td>Codigo IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}" />
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" class="btn btn-success"
							value="Salvar"> <input type="submit"
							class="btn btn-danger" value="Cancelar"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'">
							<!-- <button type="button" class="btn btn-success" alt="Salvar" title="Salvar Usuário">Salvar</button>
						<button type="button" class="btn btn-danger" alt="Cancelar" title="Cancelar Usuário" onclose="true">Cancelar</button> -->
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
					<th>Login</th>
					<th>Nome</th>
					<th>Telefone</th>
					<th>CEP</th>
					<th>Rua</th>
					<th>Bairro</th>
					<th>Cidade</th>
					<th>Codigo IBGE</th>
					<th>Excluir</th>
					<th>Editar</th>
					<th>Telefones</th>
				</tr>
			</thead>
			<tbody>
				<div class="jumbotron text-center">
					<h1>Lista de usuários cadastrados</h1>

				</div>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td style="width: 150px"><c:out value="${user.id}"></c:out></td>
						<td style="width: 150px"><c:out value="${user.login}"></c:out></td>
						<td><c:out value="${user.nome}"></c:out></td>
						<td><c:out value="${user.tel}"></c:out></td>
						<td><c:out value="${user.cep}"></c:out></td>
						<td><c:out value="${user.rua}"></c:out></td>
						<td><c:out value="${user.bairro}"></c:out></td>
						<td><c:out value="${user.cidade}"></c:out></td>
						<td><c:out value="${user.uf}"></c:out></td>
						<td><c:out value="${user.ibge}"></c:out></td>

						<td><a href="salvarUsuario?acao=delete&user=${user.id}"><button
									type="button" class="btn btn-danger" alt="Excluir"
									title="Excluir usuário">Excluir</button></a></td>
									
						<td><a href="salvarUsuario?acao=editar&user=${user.id}"><button
									type="button" class="btn btn-warning" alt="Editar"
									title="Editar usuário">Editar</button></a></td>
						
						<td><a href="salvarTelefone?acao=addFone&user=${user.id}"><button
									type="button" class="btn btn-primary" alt="Telefones"
									title="Telefones">Telefones</button></a></td>S
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o login');
				return false;

			} else if (document.getElementById("senha").value == '') {
				alert('Informe a senha');
				return false;

			} else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;

			} else if (document.getElementById("tel").value == '') {
				alert('Informe o telefone');
				return false;
			}
			return true;
		}

		$(document)
				.ready(
						function() {

							function limpa_formulário_cep() {
								// Limpa valores do formulário de cep.
								$("#rua").val("");
								$("#bairro").val("");
								$("#cidade").val("");
								$("#uf").val("");
								$("#ibge").val("");
							}

							//Quando o campo cep perde o foco.
							$("#cep")
									.blur(
											function() {

												//Nova variável "cep" somente com dígitos.
												var cep = $(this).val()
														.replace(/\D/g, '');

												//Verifica se campo cep possui valor informado.
												if (cep != "") {

													//Expressão regular para validar o CEP.
													var validacep = /^[0-9]{8}$/;

													//Valida o formato do CEP.
													if (validacep.test(cep)) {

														//Preenche os campos com "..." enquanto consulta webservice.
														$("#rua").val("...");
														$("#bairro").val("...");
														$("#cidade").val("...");
														$("#uf").val("...");
														$("#ibge").val("...");

														//Consulta o webservice viacep.com.br/
														$
																.getJSON(
																		"https://viacep.com.br/ws/"
																				+ cep
																				+ "/json/?callback=?",
																		function(
																				dados) {

																			if (!("erro" in dados)) {
																				//Atualiza os campos com os valores da consulta.
																				$(
																						"#rua")
																						.val(
																								dados.logradouro);
																				$(
																						"#bairro")
																						.val(
																								dados.bairro);
																				$(
																						"#cidade")
																						.val(
																								dados.localidade);
																				$(
																						"#uf")
																						.val(
																								dados.uf);
																				$(
																						"#ibge")
																						.val(
																								dados.ibge);
																			} //end if.
																			else {
																				//CEP pesquisado não foi encontrado.
																				limpa_formulário_cep();
																				alert("CEP não encontrado.");
																			}
																		});
													} //end if.
													else {
														//cep é inválido.
														limpa_formulário_cep();
														alert("Formato de CEP inválido.");
													}
												} //end if.
												else {
													//cep sem valor, limpa formulário.
													limpa_formulário_cep();
												}
											});
						});
	</script>

</body>
</html>