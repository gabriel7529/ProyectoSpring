package com.cibertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Habitacion;
import com.cibertec.repository.IHabitacionRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private IHabitacionRepositorio habitacionRepository;

    public List<Habitacion> obtenerTodasHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> obtenerHabitacionPorId(int id) {
        return habitacionRepository.findById(id);
    }

    public void guardarHabitacion(Habitacion habitacion) {
        habitacionRepository.save(habitacion);
    }

    public void eliminarHabitacion(int id) {
        habitacionRepository.deleteById(id);
    }
}

