package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.exepcion.Exepcion;

import java.util.List;

public interface IOficioServicio {

    void crearOficio(String nombre, String descrpcion) throws Exepcion;
    void eliminarById (String id) throws Exepcion;
    void modificarById(String id, String nombre, String descripcion) throws Exepcion;
    Oficio buscarById (String id) throws Exepcion;
    Oficio buscarByNombre (String nombre) throws Exepcion;
    List<Oficio> listarTodos();
    Oficio getOne(String id);

}
