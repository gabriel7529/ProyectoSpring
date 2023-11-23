package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cibertec.model.Cliente;
import com.cibertec.model.Habitacion;
import com.cibertec.model.Reserva;

import com.cibertec.service.ClienteService;
import com.cibertec.service.HabitacionService;
import com.cibertec.service.ReservaService;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping("/reservas")
public class ReservaController {

	private final HabitacionService habitacionService;
	private final ClienteService clienteService;
	private final ReservaService reservaServices;

	public ReservaController(ReservaService reservaService, HabitacionService habitacionService,
			ClienteService clienteService,ReservaService reservaServices) {
		this.habitacionService = habitacionService;
		this.clienteService = clienteService;
		this.reservaServices = reservaService;

	}

	private final List<Habitacion> carrito = new ArrayList<>();

	private List<Cliente> clientes = new ArrayList<>();

	@GetMapping("/verCarrito")
	public String verCarrito(Model model) {

		clientes = clienteService.obtenerTodosClientes();
		model.addAttribute("habitacionEnCarrito", carrito.size() > 0 ? carrito.get(0) : null);
		model.addAttribute("clientes", clientes);
		model.addAttribute("reserva", new Reserva());
		return "carrito";
	}

	@PostMapping("/agregarAlCarrito")
	public String agregarAlCarrito(@RequestParam("id") int habitacionId, Model model) {
		Habitacion habitacion = habitacionService.obtenerHabitacionPorId(habitacionId).orElse(null);
		if (habitacion != null) {
			carrito.add(habitacion);
			model.addAttribute("habitacionesEnCarrito", carrito);
		}

		return "redirect:/reservas/verCarrito";
	}
	
	@PostMapping("/procesarCompra")
	public String procesarCompra(@ModelAttribute("reserva") Reserva reserva, Model model) {
	    if (carrito.size() > 0) {
	        try {
	            Habitacion habitacionEnCarrito = carrito.get(0);
	            int clienteId = reserva.getCliente().getId();
	            Cliente clienteDeseado = clienteService.obtenerClientePorId(clienteId)
	                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado. ID: " + clienteId));
	     
	        	Reserva reservabd = new Reserva();
	        	reservabd.setHabitacion(habitacionEnCarrito);
	            reservabd.setFechaFin(reserva.getFechaFin());
	            reservabd.setFechaInicio(reserva.getFechaInicio());
	            reservabd.setCliente(clienteDeseado);
	            reservabd.setTotal(habitacionEnCarrito.getPrecio());
	            
	            System.out.println("Uso " + reservabd);
	            
	            if (clienteDeseado != null && habitacionEnCarrito != null) {
	                
	                reservaServices.guardarReserva(reservabd);
	            } else {
	                System.out.println("Cliente no encontrado. ID: " + clienteId + " " + habitacionEnCarrito.getId());
	            }
	            model.addAttribute("reserva", reservabd);
	            return "pago_compra";
	            
	        } catch (Exception e) {
	            model.addAttribute("error", "Error al procesar la reserva. Inténtalo nuevamente. " + e.getMessage());
	            return "error";
	        }
	    } else {
	        return "error";
	    }
	    	
	}
	
	@GetMapping("/confirmarpago")
	public String confirmarPago(@RequestParam("reservaId") int reservaId, Model model) {
	   
		try {
	        
			  Reserva reserva = reservaServices.obtenerReservaPorId(reservaId)
		                .orElseThrow(() -> new RuntimeException("Reserva no encontrada. ID: " + reservaId));
	        reservaServices.actualizarEstado(reserva, "Cancelado");

	        model.addAttribute("mensajeConfirmacion", "¡Pago confirmado! Gracias por tu compra.");
	        carrito.clear();
	        return "confirmacion_pago";
	        
	    } catch (Exception e) {
	        
	        model.addAttribute("error", "Error al confirmar el pago. " + e.getMessage());
	        return "error";
	    }
	}
	
	

	@GetMapping("/cancelar")
	public String cancelarCompra() {
		carrito.clear();
		return "redirect:/habitaciones/catalogo";
	}
}
