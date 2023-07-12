package com.serviciosYa.servicios;
import com.serviciosYa.entidades.*;
import com.serviciosYa.enums.Estado;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.SolicitudRepositorio;
import com.serviciosYa.servicios.interfaces.IClienteServicio;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import com.serviciosYa.servicios.interfaces.ISolicitudServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SolicitudServicio implements ISolicitudServicio {

    SolicitudRepositorio  solicitudRepositorio;
    IProveedorServicio proveedorServicio;
    IClienteServicio clienteServicio;

    @Override
    public void crearSolicitud(String idCliente, String idProveedor, String descripcion, Float costo, String comentario)throws Exepcion{


        Solicitud solicitud = new Solicitud();
        Proveedor proveedor = proveedorServicio.getOne(idProveedor);
        Cliente cliente = clienteServicio.getOne(idCliente);

        validar (cliente,proveedor,descripcion,costo);

        solicitud.setCliente(cliente);
        solicitud.setProveedor(proveedor);
        solicitud.setDescripcion(descripcion);
        solicitud.setEstado(Estado.PENDIENTE);
        solicitud.setCosto(costo);
        solicitud.setComentario(comentario);
        solicitud.setFechaServicio(new Date());

        solicitudRepositorio.save(solicitud);
    }
    @Transactional
    public void modificarById (String id, String descripcion, Estado estado, Float costo, String comentario) throws Exepcion {

        Solicitud solicitud =buscarByID(id);

       validar2 (descripcion,costo);

        solicitud.setDescripcion(descripcion);
        solicitud.setEstado(estado);
        solicitud.setCosto(costo);
        solicitud.setComentario(comentario);
        solicitud.setFechaServicio(new Date());

        solicitudRepositorio.save(solicitud);
    }
    public Solicitud getOne(String id) {
        return solicitudRepositorio.getReferenceById(id);
    }
    public Solicitud buscarByID(String id)throws Exepcion{

        Optional<Solicitud> repuesta = solicitudRepositorio.findById(id);

        return repuesta.orElseThrow(()-> new Exepcion("Solicitud no existe"));
    }
    @Transactional
    public void eliminarById(String id) throws Exepcion {

        Solicitud solicitud = buscarByID(id);
        solicitudRepositorio.delete(solicitud);
    }

    @Override
    public List<Solicitud> listarSolicitudes(String id){

        Usuario usuario;
        try {
            usuario = clienteServicio.buscarByID(id);
            return new ArrayList<>(solicitudRepositorio.buscarPorIdCliente(usuario.getId()));
        } catch (Exepcion e) {
            try {
                usuario = proveedorServicio.buscarByID(id);
                return new ArrayList<>(solicitudRepositorio.buscarPorIdProveedor(usuario.getId()));
            } catch (Exepcion ex) {
                throw new UsernameNotFoundException("Usuario no registrado");
            }
        }
    }
    public List<Solicitud> listarSolicitudes(){
        return new ArrayList<>(solicitudRepositorio.findAll());
    }

    private void validar (Cliente cliente,Proveedor proveedor, String descripcion, Float costo) throws Exepcion{

        if(cliente == null){
            throw new Exepcion("La celda cliente esta vacia");
        }
        if(proveedor == null){
            throw new Exepcion("La celda proveedor esta vacia");
        }

        if (descripcion.isEmpty()){
            throw new Exepcion("La descripcion esta vacia ");
        }

        if (costo < 0){
            throw new Exepcion("Se debe asignar un un costo base ");//  pendiente revisarla como se ejecutara desde el formulario
        }

    }

    private void validar2 (String descripcion, Float costo) throws Exepcion{

        if (descripcion.isEmpty()){
            throw new Exepcion("La descripcion esta vacia ");
        }

        if (costo < 0){
            throw new Exepcion("Se debe asignar un un costo base ");//  pendiente revisarla como se ejecutara desde el formulario
        }

    }

}
