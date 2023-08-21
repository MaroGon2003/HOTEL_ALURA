package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Huesped;


public class HuespedDAO {
	
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {

		try (con) {

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPED(nombre, apellido, fechaNacimiento, nacionalidad, telefono, id_Reserva)" + " VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {

				ejecutandoRegistro(huesped, statement);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}// fin guardado
	
	private void ejecutandoRegistro(Huesped huesped, PreparedStatement statement) throws SQLException {

		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5,huesped.getTelefono());  
		statement.setInt(6, huesped.getIdReserva());
		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {

			while (resultSet.next()) {

				huesped.setId(resultSet.getInt(1));
				System.out.println(String.format("El ID que fue ingrasado fue %S", huesped));

			}

		}

	}// fin ejecutar registro

}
