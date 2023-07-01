package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Solicitud;
import com.serviciosYa.enums.Estado;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.ClienteRepositorio;
import com.serviciosYa.repositorios.ProveedorRepositorio;
import com.serviciosYa.repositorios.SolicitudRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SolicitudServicio {

    @Autowired
    private SolicitudRepositorio  solicitudRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Transactional
    public void crearSolicitud(Cliente cliente, Proveedor proveedor, String descripcion, Estado estado, float costo, String comentario, Date fechaServicio){

        Solicitud solicitud = new Solicitud();

        solicitud.setCliente(cliente);
        solicitud.setProveedor(proveedor);
        solicitud.setDescripcion(descripcion);
        solicitud.setEstado(estado);
        solicitud.setCosto(costo);
        solicitud.setComentario(comentario);
        solicitud.setFechaServicio(fechaServicio);

        solicitudRepositorio.save(solicitud);

    }
    @Transactional
    public void modificarById (String id, Proveedor proveedor, Oficio oficio, String descripcion, Estado estado, float costo, String comentario, Date fechaServicio) throws Exepcion {

        Solicitud solicitud= buscarByID(id);

        solicitud.setProveedor(proveedor);
        solicitud.setDescripcion(descripcion);
        solicitud.setEstado(estado);
        solicitud.setCosto(costo);
        solicitud.setComentario(comentario);
        solicitud.setFechaServicio(fechaServicio);
        solicitudRepositorio.save(solicitud);
    }
    @Transactional
    public Solicitud buscarByID(String id)throws Exepcion{

        Optional<Solicitud> repuesta = solicitudRepositorio.findById(id);

        return repuesta.orElseThrow(()-> new Exepcion("Solicitud no existe"));
    }
    @Transactional
    public void eliminarById (String id) throws Exepcion {

        Solicitud solicitud = buscarByID(id);
        solicitud.setEstado(Estado.FINALIZADO);
        solicitudRepositorio.save(solicitud);
    }

    public List<Solicitud> listarSolicitudes (){
        return new ArrayList<>(solicitudRepositorio.findAll());
    }


}
