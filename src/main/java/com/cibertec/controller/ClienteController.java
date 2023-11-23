package com.cibertec.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cibertec.model.Cliente;
import com.cibertec.model.Reserva;
import com.cibertec.service.ClienteService;
import com.cibertec.service.ReservaService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;
	private final ReservaService reservaService;

	public ClienteController(ClienteService clienteService, ReservaService reservaService) {
		this.clienteService = clienteService;
		this.reservaService = reservaService;
	}

	private final List<Reserva> carritoReservas = new ArrayList<>();

	@PostMapping("/agregarAlCarrito")
	public String agregarAlCarrito(@RequestParam("habitacionId") Long habitacionId,
			@RequestParam("cantidad") int cantidad) {
		return "redirect:/clientes/carritoReservas";
	}

	@GetMapping("/carritoReservas")
	public String carritoReservas(Model model) {
		model.addAttribute("reservasEnCarrito", carritoReservas);
		return "carrito_reservas";
	}

	@PostMapping("/confirmarReserva")
	public String confirmarReserva(@ModelAttribute("cliente") Cliente cliente) {

		return "redirect:/clientes/procesoPago";
	}

	@PostMapping("/completarPago")
	public String completarPago(@RequestParam("metodoPago") String metodoPago) {

		return "redirect:/clientes/confirmacionReserva";
	}

}
