package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository <Cliente,String> {

    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByNombreAndApellido(String nombre, String apellido);

    static final String QUERY = "SELECT c FROM Cliente c WHERE c.activo = TRUE";
    @Query(QUERY)
    List<Cliente> listarTodosActivos();

}
