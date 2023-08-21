package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
