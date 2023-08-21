package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class testConexion {
	
	public static void main(String[] args) throws SQLException {
		
		Connection con = new ConnectionFactory().recuperarConexion();
		
		System.out.println("Conectado");
		con.close();
		
		System.out.println("Cerrado");
		
		
		
	}

}
