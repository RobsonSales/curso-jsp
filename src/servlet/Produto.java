package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;

import dao.DaoProduto;

@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoProduto daoProduto = new DaoProduto();

	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {
				BeanProduto beanProduto = daoProduto.consultar(produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produto", beanProduto);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
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
				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			try {

				String msg = null;
				boolean podeInserir = true;

				
				if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;
				
				}else if (quantidade == null || quantidade.isEmpty()) {
						msg = "Quantidade deve ser informado";
						podeInserir = false;
					
				}else if (valor == null || valor.isEmpty()) {
					msg = "Valor R$ deve ser informado";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoProduto.validarProduto(nome)) {// QUANDO
																							// FDOR
					msg = "Produto já existe com o mesmo nome!";
					podeInserir = false;

				}

				BeanProduto produto = new BeanProduto();
				produto.setNome(nome);
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);

				if (quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));
				}

				if (valor != null && !valor.isEmpty())
					produto.setValor(Double.parseDouble(valor));

				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && daoProduto.validarProduto(nome) && podeInserir) {

					daoProduto.salvar(produto);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoProduto.atualizar(produto);
				}

				if (!podeInserir) {
					request.setAttribute("produto", produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
