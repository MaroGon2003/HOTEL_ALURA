package controller;

import java.time.LocalDate;
import java.util.List;

import DAO.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedController {

	private HuespedDAO huespedDAO;

	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperarConexion());
	}

	public void guardar(Huesped huesped) {
		huespedDAO.guardar(huesped);
	}
	
	public List<Huesped> listar(){
		return huespedDAO.listar();
	}
	
	public List<Huesped> buscarApellido(String apellido){
		return huespedDAO.buscarApellido(apellido);
	}
	
	public int modificar(String id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, String idReserva) {
		return huespedDAO.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
	}
	
	public int eliminar(String id) {
		return huespedDAO.eliminar(id);
	}

}
