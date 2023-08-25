package controller;

import java.util.List;

import DAO.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperarConexion());
	}

	/*
	 * public int modificar(String nombre, String descripcion, Integer id, Integer
	 * cantidad) {
	 * 
	 * return reservaDAO.modificar(nombre, descripcion, id, cantidad);
	 * 
	 * }
	 * 
	 * public int eliminar(Integer id) {
	 * 
	 * return reservaDAO.eliminar(id);
	 * 
	 * }
	 * 
	 * 
	 */

	public void guardar(Reserva reserva) {

		reservaDAO.guardar(reserva);

	}

	public List<Reserva> listar() {

		return reservaDAO.listar();

	}

	public List<Reserva> buscarId(String id) {
		return reservaDAO.buscarId(id);
	}

}
