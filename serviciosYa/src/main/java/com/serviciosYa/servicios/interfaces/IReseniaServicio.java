package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Resenia;
import com.serviciosYa.enums.Estrella;
import com.serviciosYa.exepcion.Exepcion;

import java.util.List;

public interface IReseniaServicio {

    public Resenia crear(String comentario, String estrella, String idProveedor,String idSolicitud) throws Exepcion;
    public void eliminarById (String id) throws Exepcion;
    public void modificarById (String id, String comentario, String estrella, Proveedor proveedor) throws Exepcion;
    public List<Resenia> lsitarResenia();
    public Resenia buscarById (String id) throws Exepcion;
    public Resenia getOne(String id);
}
