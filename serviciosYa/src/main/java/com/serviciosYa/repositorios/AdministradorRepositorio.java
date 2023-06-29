package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Administrador;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministradorRepositorio extends JpaRepository <Administrador,String> {
    Optional<Administrador> findByEmail(String email);
    Optional<Administrador> findByNombreAndApellido(String nombre, String apellido);

    static final String QUERY = "SELECT a FROM Administrador a WHERE a.activo = TRUE";
    @Query(QUERY)
    List<Administrador> listarTodosActivos();
}
