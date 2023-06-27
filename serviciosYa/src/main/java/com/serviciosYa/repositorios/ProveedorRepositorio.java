package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepositorio extends JpaRepository<Proveedor,String> {

    Optional<Proveedor> findByEmail(String email);

}
