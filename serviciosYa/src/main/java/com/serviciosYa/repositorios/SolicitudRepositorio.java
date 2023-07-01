package com.serviciosYa.repositorios;
import com.serviciosYa.entidades.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface SolicitudRepositorio extends JpaRepository  <Solicitud,String> {

    Optional<Solicitud> findById (String id);

    @Query("SELECT s FROM Solicitud s WHERE s.Cliente.nombre = :nombre")
    public List<Solicitud> buscarPorCliente(@Param("nombre")String nombre);

    @Query("SELECT s FROM Solicitud s WHERE s.Proveedor.nombre = :nombre")
    public List<Solicitud> buscarPorProveedor(@Param("nombre")String nombre);





}
