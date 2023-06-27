package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.repositorios.OficioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OficioServicio {

    private OficioRepositorio oficioRepositorio;

    public List<Oficio> listarTodos() {
        return oficioRepositorio.findAll();
    }
}
