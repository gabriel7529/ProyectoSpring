package com.cibertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Reserva;
import com.cibertec.repository.IReservaRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

	@Autowired
	private IReservaRepositorio reservaRepository;

	public List<Reserva> obtenerTodasReservas() {
		return reservaRepository.findAll();
	}

	public Optional<Reserva> obtenerReservaPorId(int id) {
		return reservaRepository.findById(id);
	}

	public void guardarReserva(Reserva reserva) {
		reservaRepository.save(reserva);
	}

	public void eliminarReserva(int id) {
		reservaRepository.deleteById(id);
	}
}
