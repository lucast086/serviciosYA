package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OficioRepositorio extends JpaRepository<Oficio,String> {
    Optional<Oficio> findByNombre(String nombre);
}
