package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJSP;
import beans.BeanTelefone;
import dao.DaoTelefone;
import dao.DaoUsuario;

@WebServlet("/salvarTelefone")
public class Telefone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefone daoTelefone = new DaoTelefone();

	public Telefone() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {

			String acao = request.getParameter("acao");

			if (acao.equals("addFone")) {
				String user = request.getParameter("user");
				BeanCursoJSP usuario = daoUsuario.consultar(user);

				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("/cadastrotelefone.jsp");
				request.setAttribute("telefone", daoTelefone.listar(usuario.getId()));
				request.setAttribute("msg", "Telefone salvo com sucesso");
				view.forward(request, response);

			}else if (acao.equals("delete")) {
				String foneId = request.getParameter("foneId");
				daoTelefone.delete(foneId);
				
				BeanCursoJSP beanCursoJSP = (BeanCursoJSP) request.getSession().getAttribute("userEscolhido");
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastrotelefone.jsp");
				request.setAttribute("telefone", daoTelefone.listar(beanCursoJSP.getId()));
				request.setAttribute("msg", "Telefone removido com sucesso");
				view.forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		try {
			BeanCursoJSP beanCursoJSP = (BeanCursoJSP) request.getSession().getAttribute("userEscolhido");

			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			BeanTelefone telefone = new BeanTelefone();
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setUsuario(beanCursoJSP.getId());

			daoTelefone.salvar(telefone);

			request.getSession().setAttribute("userEscolhido", beanCursoJSP);
			request.setAttribute("userEscolhido", beanCursoJSP);

			RequestDispatcher view = request.getRequestDispatcher("/cadastrotelefone.jsp");
			request.setAttribute("telefone", daoTelefone.listar(beanCursoJSP.getId()));
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
