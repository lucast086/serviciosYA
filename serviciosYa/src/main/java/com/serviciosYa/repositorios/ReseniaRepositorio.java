package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Resenia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseniaRepositorio extends JpaRepository<Resenia,String> {
}
