package com.cibertec.model;

import java.sql.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "habitacion_id", nullable = false)
	private Habitacion habitacion;

	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	@Override
	public String toString() {
		return "Reserva [ cliente=" + cliente + ", habitacion=" + habitacion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", total=" + total + ", estado=" + estado + "]";
	}

	private double total;

	private String estado = "Pendiente";

	public Reserva(int id, Cliente cliente, Habitacion habitacion, Date fechaInicio, Date fechaFin, double total,
			String estado) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.habitacion = habitacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.total = total;
		this.estado = estado;
	}
	
	public Reserva() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	 
}
