package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Solicitud;
import com.serviciosYa.enums.Estado;
import com.serviciosYa.exepcion.Exepcion;

import java.util.Date;
import java.util.List;

public interface ISolicitudServicio {

    void crearSolicitud (String idCliente, String idProveedor, String descripcion, Float costo, String Comentario) throws Exepcion;
    void modificarById (String id,Cliente cliente,Proveedor proveedor, String descripcion, Estado estado, Float costo, String comentario) throws Exepcion;

    Solicitud buscarByID(String id)throws Exepcion;
    void eliminarById (String id) throws Exepcion;
    Solicitud getOne (String id);

    List<Solicitud> listarSolicitudes ();

}
