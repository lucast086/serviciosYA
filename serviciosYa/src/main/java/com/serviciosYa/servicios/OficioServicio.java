package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.OficioRepositorio;
import com.serviciosYa.servicios.interfaces.IOficioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OficioServicio implements IOficioServicio {

    private OficioRepositorio oficioRepositorio;

    @Transactional
    public void crearOficio(String nombre, String descrpcion, String codigo, String color) throws Exepcion {

        validar(nombre, descrpcion, codigo, color);

        Oficio oficio = new Oficio();

        oficio.setNombre(nombre);
        oficio.setDescripcion(descrpcion);
        oficio.setCodigoIcono(codigo);
        oficio.setColorTarjeta(color);

        oficioRepositorio.save(oficio);

    }

    @Transactional
    public void eliminarById (String id) throws Exepcion {
        Oficio oficio = buscarById(id);
        oficioRepositorio.delete(oficio);
    }

    @Transactional
    public void modificarById(String id, String nombre, String descripcion, String codigo, String color) throws Exepcion {
        Oficio oficio = buscarById(id);

        validar(nombre,descripcion,codigo,color);

        oficio.setNombre(nombre);
        oficio.setDescripcion(descripcion);
        oficio.setCodigoIcono(codigo);
        oficio.setColorTarjeta(color);

        oficioRepositorio.save(oficio);

    }


    public Oficio buscarById (String id) throws Exepcion {
        Optional <Oficio> repuesta = oficioRepositorio.findById(id);
        return repuesta.orElseThrow(()-> new Exepcion("El oficio no existe"));
    }

    public Oficio buscarByNombre (String nombre) throws Exepcion {
        Optional <Oficio> repuesta = oficioRepositorio.findByNombre(nombre);
        return repuesta.orElseThrow(()-> new Exepcion("El Oficio no existe"));
    }

    public List<Oficio> listarTodos() {
        return oficioRepositorio.findAll();
    }

    public Oficio getOne(String id){
        return oficioRepositorio.getReferenceById(id);
    }

    private void validar (String nombre, String descripcion, String codigo, String color) throws Exepcion {

        if (codigo.isEmpty()){
            throw new Exepcion("La celda del codigo esta vacia");
        }    if (color.isEmpty()){
            throw new Exepcion("La celda del color esta vacia");
        }
        if (nombre.isEmpty()){
            throw new Exepcion("La celda del nombre esta vacia");
        }
        if (descripcion.isEmpty()){
            throw new Exepcion("La celda del descripcion esta vacia");
        }
    }
}

