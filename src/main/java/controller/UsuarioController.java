package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Usuario;
import views.Login;
import views.MenuUsuario;

public class UsuarioController implements ActionListener{
	
	private Login vista;
	
	public UsuarioController(Login vista) {
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nombre = vista.getNombre();
		String Contraseña = vista.getContraseña();
		
		if(Usuario.validarUsuario(nombre, Contraseña)) {
			MenuUsuario menu = new MenuUsuario();
			menu.setVisible(true);
			vista.dispose();
		}else {
			JOptionPane.showMessageDialog(vista, "El usuario o contraseña no son correctas");
		}
	}

}
