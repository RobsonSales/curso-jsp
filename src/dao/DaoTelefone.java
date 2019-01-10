package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.BeanProduto;
import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefone {

	private Connection connection;

	public DaoTelefone() {

		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanTelefone telefones) {

		try {

			String sql = "insert into telefones (numero, tipo, usuario) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefones.getNumero());
			insert.setString(2, telefones.getTipo());
			insert.setLong(3, telefones.getUsuario());

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

	public List<BeanTelefone> listar(Long user) throws Exception {
		List<BeanTelefone> listar = new ArrayList<BeanTelefone>();

		String sql = "select * from telefones where usuario = " + user;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanTelefone beanTelefone = new BeanTelefone();
			beanTelefone.setId(resultSet.getLong("id"));
			beanTelefone.setNumero(resultSet.getString("numero"));
			beanTelefone.setTipo(resultSet.getString("tipo"));
			beanTelefone.setUsuario(resultSet.getLong("usuario"));
			listar.add(beanTelefone);
		}

		return listar;
	}

	public void delete(String id) {
		String sql = "delete from telefones where id = '" + id + "'";

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

}
