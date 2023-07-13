package com.serviciosYa.repositorios;
import com.serviciosYa.entidades.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitudRepositorio extends JpaRepository  <Solicitud,String> {

    Optional<Solicitud> findById (String id);

    @Query(nativeQuery = true, name = "Solicitud.findByClienteId")
    List<Solicitud> buscarPorIdCliente(@Param("userId")String userId);

    @Query(nativeQuery = true, name = "Solicitud.findByProveedorId")
    List<Solicitud> buscarPorIdProveedor(@Param("userId")String userId);

}
