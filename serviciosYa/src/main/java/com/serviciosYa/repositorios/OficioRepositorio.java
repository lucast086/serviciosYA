package com.serviciosYa.repositorios;

import com.serviciosYa.entidades.Oficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficioRepositorio extends JpaRepository<Oficio,String> {


}
