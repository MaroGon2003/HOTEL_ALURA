package controller;

import java.time.LocalDate;
import java.util.List;

import DAO.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperarConexion());
	}

	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}

	public List<Reserva> listar() {
		return reservaDAO.listar();
	}

	public List<Reserva> buscarId(String id) {
		return reservaDAO.buscarId(id);
	}
	
	public int modificar(String id, LocalDate fechaEntrada, LocalDate fechaSalida, String  valor, String formaPago) {
		return reservaDAO.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
	}
	
	public int eliminar(String id) {
		return reservaDAO.eliminar(id);
	}

}
