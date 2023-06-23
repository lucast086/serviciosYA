package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository <Usuario,String> {

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNombreAndApellido(String nombre, String apellido);
}
