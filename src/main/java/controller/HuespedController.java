package controller;

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

}
