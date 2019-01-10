package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJSP;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				BeanCursoJSP beanCursoJSP = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("user", beanCursoJSP);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String tel = request.getParameter("tel");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");
			String ibge = request.getParameter("ibge");

			BeanCursoJSP usuario = new BeanCursoJSP();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTel(tel);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setUf(uf);
			usuario.setIbge(ibge);
			

			try {

				if (login == null || login.isEmpty()) {
					request.setAttribute("msg", "O login deve ser informado!");
					request.setAttribute("user", usuario);

				} else if (senha == null || senha.isEmpty()) {
					request.setAttribute("msg", "A senha deve ser informada!");
					request.setAttribute("user", usuario);
					
				} else if (nome == null || nome.isEmpty()) {
					request.setAttribute("msg", "O nome deve ser informado!");
					request.setAttribute("user", usuario);
					
				} else if (tel == null || tel.isEmpty()) {
					request.setAttribute("msg", "O telefone deve ser informado!");
					request.setAttribute("user", usuario);

				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg", "O usuário já existe!!");
					request.setAttribute("user", usuario);

				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					request.setAttribute("msg_senha", "Senha já cadastrada para outro usuário");
					request.setAttribute("user", usuario);
				} else if (id == null
						|| id.isEmpty() && daoUsuario.validarLogin(login) && daoUsuario.validarSenha(senha)) {
					daoUsuario.salvar(usuario);
					request.setAttribute("msg", "Usuário cadastrado com sucesso");

				} else if (id != null && !id.isEmpty()) {
					daoUsuario.atualizar(usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastrousuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
