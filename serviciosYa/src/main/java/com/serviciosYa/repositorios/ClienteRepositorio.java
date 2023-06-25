package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository <Cliente,String> {

    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByNombreAndApellido(String nombre, String apellido);

}
