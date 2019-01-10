package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import beans.BeanCursoJSP;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {

		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJSP usuario) {

		try {

			String sql = "insert into usuario (login, senha, nome, tel, cep, rua, bairro, cidade, uf, ibge) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTel());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getUf());
			insert.setString(10, usuario.getIbge());
			
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanCursoJSP> listar() throws Exception {
		List<BeanCursoJSP> listar = new ArrayList<BeanCursoJSP>();

		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
			beanCursoJSP.setId(resultSet.getLong("id"));
			beanCursoJSP.setLogin(resultSet.getString("login"));
			beanCursoJSP.setSenha(resultSet.getString("senha"));
			beanCursoJSP.setNome(resultSet.getString("nome"));
			beanCursoJSP.setTel(resultSet.getString("tel"));
			beanCursoJSP.setCep(resultSet.getString("cep"));
			beanCursoJSP.setRua(resultSet.getString("rua"));
			beanCursoJSP.setBairro(resultSet.getString("bairro"));
			beanCursoJSP.setCidade(resultSet.getString("cidade"));
			beanCursoJSP.setUf(resultSet.getString("uf"));
			beanCursoJSP.setIbge(resultSet.getString("ibge"));

			listar.add(beanCursoJSP);
		}
		return listar;
	}

	public void delete(String id) {
		String sql = "delete from usuario where id = '" + id + "'";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJSP consultar(String id) throws Exception {
		String sql = "select * from usuario where id='" + id + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
			beanCursoJSP.setId(resultSet.getLong("id"));
			beanCursoJSP.setLogin(resultSet.getString("login"));
			beanCursoJSP.setSenha(resultSet.getString("senha"));
			beanCursoJSP.setNome(resultSet.getString("nome"));
			beanCursoJSP.setTel(resultSet.getString("tel"));
			beanCursoJSP.setCep(resultSet.getString("cep"));
			beanCursoJSP.setRua(resultSet.getString("rua"));
			beanCursoJSP.setBairro(resultSet.getString("bairro"));
			beanCursoJSP.setCidade(resultSet.getString("cidade"));
			beanCursoJSP.setUf(resultSet.getString("uf"));
			beanCursoJSP.setIbge(resultSet.getString("ibge"));

			return beanCursoJSP;
		}

		return null;
	}

	public void atualizar(BeanCursoJSP usuario) {

		try {

			String sql = "update usuario set login = ?, senha = ?, nome = ?, tel = ?, cep = ?, rua = ?, bairro = ?, "
					+ "cidade = ?, uf = ?, ibge = ? where id = " + usuario.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getTel());
			preparedStatement.setString(5, usuario.getCep());
			preparedStatement.setString(6, usuario.getRua());
			preparedStatement.setString(7, usuario.getBairro());
			preparedStatement.setString(8, usuario.getCidade());
			preparedStatement.setString(9, usuario.getUf());
			preparedStatement.setString(10, usuario.getIbge());
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login='" + login + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			

			return resultSet.getInt("qtd") <= 0;/*return true*/
		}

		return false;
	}
	
	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha='" + senha + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			

			return resultSet.getInt("qtd") <= 0;/*return true*/
		}

		return false;
	}

}
