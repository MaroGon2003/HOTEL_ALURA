package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaDAO {

	final private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {

		try (con) {

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas(fecha_entrada, fecha_salida, valor, forma_de_pago)"
							+ " VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {

				ejecutandoRegistro(reserva, statement);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}// fin guardado

	private void ejecutandoRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
		
		statement.setObject(1, reserva.getFechaEntrada());
		statement.setObject(2, reserva.getFechaSalida());
		statement.setString(3, reserva.getValor());
		statement.setString(4, reserva.getFormaPago());
		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {

			while (resultSet.next()) {

				reserva.setId(resultSet.getInt(1));
				System.out.println(String.format("El ID que fue ingrasado fue %S", reserva));

			}

		}

	}// fin ejecutar registro
	
	public List<Reserva> listar() {

		List<Reserva> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperarConexion();

		try (con) {

			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_DE_PAGO FROM reservas");

			try (statement) {

				statement.execute();

				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					
					LocalDate fechaEntrada = LocalDate.parse(resultSet.getString("FECHA_ENTRADA"));
					LocalDate fechaSalida = LocalDate.parse(resultSet.getString("FECHA_SALIDA"));
					
					Reserva fila = new Reserva(resultSet.getInt("ID"),fechaEntrada ,fechaSalida,
							resultSet.getString("VALOR"), resultSet.getString("FORMA_DE_PAGO"));

					resultado.add(fila);
				}

				return resultado;

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}// fin listar
	
	public List<Reserva> buscarId(String id) {

		List<Reserva> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperarConexion();

		try (con) {

			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_DE_PAGO FROM reservas WHERE ID = ?");

			try (statement) {
				
				statement.setString(1,id);

				statement.execute();

				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					
					LocalDate fechaEntrada = LocalDate.parse(resultSet.getString("FECHA_ENTRADA"));
					LocalDate fechaSalida = LocalDate.parse(resultSet.getString("FECHA_SALIDA"));
					
					Reserva fila = new Reserva(resultSet.getInt("ID"),fechaEntrada ,fechaSalida,
							resultSet.getString("VALOR"), resultSet.getString("FORMA_DE_PAGO"));

					resultado.add(fila);
				}

				return resultado;

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}// fin buscar por id
	
	public int modificar(String id, LocalDate fechaEntrada, LocalDate fechaSalida, String  valor, String formaPago) {

		try {

			final PreparedStatement statement = con.prepareStatement(
					"UPDATE RESERVAS SET FECHA_ENTRADA = ?, FECHA_SALIDA = ? , VALOR = ?, FORMA_DE_PAGO = ? WHERE ID = ?");

			try (statement) {
				
				statement.setObject(1, java.sql.Date.valueOf(fechaEntrada));
				statement.setObject(2, java.sql.Date.valueOf(fechaSalida));
				statement.setString(3, valor);
				statement.setString(4, formaPago);
				statement.setString(5, id);

				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	

}
