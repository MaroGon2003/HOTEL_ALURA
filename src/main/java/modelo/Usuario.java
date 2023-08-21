package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factory.ConnectionFactory;

public class Usuario {
	
	private String nombre;
	private String contraseña;
	
	public Usuario(String nombre, String contraseña) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
	}
	
	public static boolean validarUsuario(String nombre, String contraseña) {
		ConnectionFactory con = new ConnectionFactory();
		Connection conc = null;
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			conc = con.recuperarConexion();
			state = conc.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? and contraseña =?");
			state.setString(1, nombre);
			state.setString(2, contraseña);
			result = state.executeQuery();
			return result.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			
			try {
				
				if(result != null) {
					result.close();
				}
				if(state != null) {
					state.close();
				}
				if(conc != null) {
					conc.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	
		}
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
	

}
