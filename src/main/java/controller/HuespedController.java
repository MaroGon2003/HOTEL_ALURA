package controller;

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

}
