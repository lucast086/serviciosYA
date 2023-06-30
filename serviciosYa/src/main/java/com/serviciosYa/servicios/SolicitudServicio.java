package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Solicitud;
import com.serviciosYa.enums.Estado;
import com.serviciosYa.repositorios.ClienteRepositorio;
import com.serviciosYa.repositorios.ProveedorRepositorio;
import com.serviciosYa.repositorios.SolicitudRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@AllArgsConstructor
public class SolicitudServicio {

    @Autowired
    private SolicitudRepositorio  SolicitudRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
    public void crearSolicitud(String id, Cliente cliente, Proveedor proveedor, Oficio oficio, String descripcion, Estado estado, float costo, String Comentario, Date fechaServicio){

        Solicitud solicitud = new Solicitud();

        solicitud.setCliente(cliente);
        solicitud.setProveedor(proveedor);
        solicitud.setOficio(oficio);
        solicitud.setDescripcion(descripcion);
        solicitud.setEstado(estado);
        solicitud.setCosto(costo);
        solicitud.setComentario(solicitud.getComentario());
        solicitud.setFechaServicio(fechaServicio);

        SolicitudRepositorio.save(solicitud);



    }
}
