package com.serviciosYa.servicios;
import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Solicitud;
import com.serviciosYa.enums.Estado;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.SolicitudRepositorio;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import com.serviciosYa.servicios.interfaces.ISolicitudServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void crearSolicitud(Cliente cliente, String idProveedor, String descripcion, Estado estado, float costo, String comentario, Date fechaServicio)throws Exepcion{

     //   validar (cliente,idProveedor,descripcion,estado,costo,fechaServicio);

        Solicitud solicitud = new Solicitud();
        Proveedor proveedor = proveedorServicio.getOne(idProveedor);

        solicitud.setCliente(cliente);
        solicitud.setProveedor(proveedor);
        solicitud.setDescripcion(descripcion);
        solicitud.setEstado(estado);
        solicitud.setCosto(0f);
        solicitud.setComentario(comentario);
        solicitud.setFechaServicio(new Date());

        solicitudRepositorio.save(solicitud);
    }
    @Transactional
    public void modificarById (String id, Cliente cliente,String idProveedor, String descripcion, Estado estado, float costo, String comentario, Date fechaServicio) throws Exepcion {

        Solicitud solicitud =buscarByID(id);
        Proveedor proveedor = proveedorServicio.getOne(idProveedor);

        validar (cliente,idProveedor,descripcion,estado,costo,fechaServicio);

        solicitud.setId(id);
        solicitud.setCliente(cliente);
        solicitud.setProveedor(proveedor);
        solicitud.setDescripcion(descripcion);
        solicitud.setEstado(estado);
        solicitud.setCosto(0f);
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
    public void eliminarById (String id) throws Exepcion {

        Solicitud solicitud = buscarByID(id);
        solicitudRepositorio.delete(solicitud);
    }

    @Override
    public List<Solicitud> listarSolicitudes (){
        return new ArrayList<>(solicitudRepositorio.findAll());
    }
    private void validar (Cliente cliente, String idProveedor, String descripcion, Estado estado, float costo, Date fechaServicio) throws Exepcion{

        if(cliente == null){
            throw new Exepcion("La celda cliente esta vacia");
        }

        if (idProveedor == null){
            throw new Exepcion("La celda proveedor esta vacia");
        }

        if (descripcion.isEmpty()){
            throw new Exepcion("La descripcion esta vacia ");
        }

        if (estado == null){
            throw new Exepcion(" Se debe asignar un estado a la solicitud"); // pendiente revisarla como se ejecutara desde el formulario
        }
        if (costo < 0){
            throw new Exepcion("Se debe asignar un un costo base ");//  pendiente revisarla como se ejecutara desde el formulario
        }

        if (fechaServicio == null){
            throw new Exepcion("La fecha de la solicitud esta vacia");
        }
    }

}
