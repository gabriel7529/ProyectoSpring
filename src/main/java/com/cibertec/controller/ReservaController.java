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

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/reservas")
public class ReservaController {

	private final HabitacionService habitacionService;
	private final ClienteService clienteService;

	public ReservaController(ReservaService reservaService, HabitacionService habitacionService,
			ClienteService clienteService) {
		this.habitacionService = habitacionService;
		this.clienteService = clienteService;

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
			carrito.clear();
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

	            reserva.setCliente(clienteDeseado);	          
	            reserva.setHabitacion(habitacionEnCarrito);
	            reserva.setTotal(habitacionEnCarrito.getPrecio());
	            
        	            
          
            
	         
	            model.addAttribute("reservaId", reserva.getId());
	            model.addAttribute("clienteNombre", clienteDeseado.getNombre());
	            model.addAttribute("habitacionTipo", habitacionEnCarrito.getTipo());
	            model.addAttribute("montoTotal", reserva.getTotal());

	            return "pago_compra";
	        } catch (Exception e) {
	            model.addAttribute("error", "Error al procesar la reserva. Inténtalo nuevamente. " + e.getMessage());
	            return "error";
	        }
	    } else {
	        return "error";
	    }
	}

	@GetMapping("/confirmarPago")
	public String confirmarPago(Model model) {
	    
	    model.addAttribute("mensajeConfirmacion", "¡Pago confirmado! Gracias por tu compra.");
	    return "confirmacion_pago";
	}
	
	

	@GetMapping("/cancelar")
	public String cancelarCompra() {
		carrito.clear();
		return "redirect:/habitaciones/catalogo";
	}
}
