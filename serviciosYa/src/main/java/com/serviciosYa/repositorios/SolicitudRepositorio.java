package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Solicitud;
import com.serviciosYa.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolicitudRepositorio extends JpaRepository  <Solicitud,String> {

    Optional<Solicitud> findById (String id);
    //realizar busquedas por cliente-proveedor,oficio, estado

}
