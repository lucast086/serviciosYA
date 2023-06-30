package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.enums.Estado;
import com.serviciosYa.exepcion.Exepcion;

import java.util.Date;

public interface ISolicitudServicio {

    void crear(String id, Cliente cliente, Proveedor proveedor, Oficio oficio, String descripcion, Estado estado, float costo, String Comentario, Date fechaServicio) throws Exepcion;
    void modificarById (Proveedor proveedor,Oficio oficio, String descripcion, Estado estado, float costo, String comentario, Date fechaServicio ) throws Exepcion;

    void eliminarById (String id) throws Exepcion;


}
