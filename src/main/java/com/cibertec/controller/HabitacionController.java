package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.model.Habitacion;
import com.cibertec.service.HabitacionService;

import java.util.List;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping("/catalogo")
    public String verCatalogo(Model model) {
        List<Habitacion> habitaciones = habitacionService.obtenerTodasHabitaciones();
        model.addAttribute("habitaciones", habitaciones);
        return "catalogo";
    }
    
    
}
