package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.model.Cliente;

public interface IClienteRepositorio extends JpaRepository<Cliente, Integer>{

}
