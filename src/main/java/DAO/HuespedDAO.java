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
import modelo.Huesped;


public class HuespedDAO {
	
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {

		try (con) {

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes(nombre, apellido, fecha_Nacimiento, nacionalidad, telefono, id_Reserva)" + " VALUES(?,?,?,?,?,?)",
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
		statement.setObject(3, huesped.getFechaNacimiento());
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
	
	public List<Huesped> listar() {

		List<Huesped> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperarConexion();

		try (con) {

			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA FROM huespedes");

			try (statement) {

				statement.execute();

				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					
					LocalDate fechaNacimiento = LocalDate.parse(resultSet.getString("FECHA_NACIMIENTO"));
					
					Huesped fila = new Huesped(resultSet.getInt("ID"),resultSet.getString("NOMBRE"),resultSet.getString("APELLIDO"),fechaNacimiento ,resultSet.getString("NACIONALIDAD"),
							resultSet.getString("TELEFONO"), resultSet.getInt("ID_RESERVA"));

					resultado.add(fila);
				}

				return resultado;

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}// fin listar
	
	public List<Huesped> buscarApellido(String apellido) {

		List<Huesped> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperarConexion();

		try (con) {

			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA FROM huespedes WHERE APELLIDO = ? OR ID = ?");

			try (statement) {
				
				statement.setString(1, apellido);
				statement.setString(2, apellido);

				statement.execute();

				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					
					LocalDate fechaNacimiento = LocalDate.parse(resultSet.getString("FECHA_NACIMIENTO"));
					
					Huesped fila = new Huesped(resultSet.getInt("ID"),resultSet.getString("NOMBRE"),resultSet.getString("APELLIDO"),fechaNacimiento ,resultSet.getString("NACIONALIDAD"),
							resultSet.getString("TELEFONO"), resultSet.getInt("ID_RESERVA"));

					resultado.add(fila);
				}

				return resultado;

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}// fin buscar por apellido


}
