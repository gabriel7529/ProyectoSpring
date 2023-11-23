package com.cibertec.model;

import java.util.*;

public class Carrito{
    private List<Habitacion> HabitacionEnCarrito = new ArrayList<>();

    public List<Habitacion> getHabitacionEnCarrito() {
        return HabitacionEnCarrito;
    }

    public void agregarHabitacion(Habitacion producto) {
        HabitacionEnCarrito.add(producto);
    }

    public void vaciarCarrito() {
        HabitacionEnCarrito.clear();
    }
}