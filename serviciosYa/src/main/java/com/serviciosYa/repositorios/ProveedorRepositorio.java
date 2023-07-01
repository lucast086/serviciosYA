package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor,String> {



    Optional<Proveedor> findByEmail(String email);
    Optional<Proveedor> findByNombreAndApellido(String nombre, String apellido);

    static final String QUERY = "SELECT p FROM Proveedor p WHERE p.activo = TRUE";
    @Query(QUERY)
    List<Proveedor> listarTodosActivos();
}
