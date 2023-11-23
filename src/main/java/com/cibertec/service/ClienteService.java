package com.cibertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Cliente;
import com.cibertec.repository.IClienteRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepositorio clienteRepository;
    
    public ClienteService(IClienteRepositorio clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(int id) {
        return clienteRepository.findById(id);
    }

    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(int id) {
        clienteRepository.deleteById(id);
    }
}
