package com.cibertec.model;

public class ReservasForm {

	private Habitacion habitacionEnCarrito;

	private Reserva reserva;
	private int clienteId;

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Habitacion getHabitacionEnCarrito() {
		return habitacionEnCarrito;
	}

	public void setHabitacionEnCarrito(Habitacion habitacionEnCarrito) {
		this.habitacionEnCarrito = habitacionEnCarrito;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

}
