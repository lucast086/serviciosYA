package com.serviciosYa.servicios;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Resenia;
import com.serviciosYa.entidades.Solicitud;
import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.enums.Estrella;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.OficioRepositorio;
import com.serviciosYa.repositorios.ProveedorRepositorio;
import com.serviciosYa.repositorios.ReseniaRepositorio;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import com.serviciosYa.servicios.interfaces.IReseniaServicio;
import com.serviciosYa.servicios.interfaces.ISolicitudServicio;
import com.serviciosYa.servicios.interfaces.IUsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReseniaServicio implements IReseniaServicio {

    ReseniaRepositorio reseniaRepositorio;
    IProveedorServicio proveedorServicio;
    ISolicitudServicio solicitudServicio;
    IUsuarioServicio usuarioServicio;

    @Transactional
    public Resenia crear(String comentario, String estrella,String idProveedor, String idSolicitud) throws Exepcion {

        validar(comentario,idProveedor);
        Resenia resenia = new Resenia();

        Proveedor proveedor = proveedorServicio.getOne(idProveedor);

        resenia.setComentario(comentario);
        resenia.setEstrellas(estrellaFromString(estrella));
        resenia.setProveedor(proveedor);
        List<Resenia> resenias = proveedor.getResenias();
        resenias.add(resenia);
        proveedor.setResenias(resenias);
        proveedorServicio.calcularEstrellas(proveedor);
        reseniaRepositorio.save(resenia);
        solicitudServicio.completarSolicitud(idSolicitud);

        return resenia;
    }

    private Estrella estrellaFromString(String x){
        switch (x) {
            case "1" : return Estrella.UNO;
            case "2" : return Estrella.DOS;
            case "3" : return Estrella.TRES;
            case "4" : return Estrella.CUATRO;
            case "5" : return Estrella.CINCO;
        }
        return null;
    }
    @Transactional
    public void eliminarById (String id) throws Exepcion {
        Resenia resenia = buscarById(id);
        reseniaRepositorio.delete(resenia);
    }
    @Transactional
    public void modificarById (String id, String comentario, String estrella,Proveedor proveedor) throws Exepcion {
        Resenia resenia = buscarById(id);

        resenia.setComentario(comentario);
        resenia.setEstrellas(estrellaFromString(estrella));
        resenia.setProveedor(proveedor);

        reseniaRepositorio.save(resenia);

    }

    public List<Resenia> lsitarResenia(){
        return new ArrayList<>(reseniaRepositorio.findAll());
    }

    public Resenia buscarById (String id) throws Exepcion {
        Optional<Resenia> repuesta = reseniaRepositorio.findById(id);
        return repuesta.orElseThrow(()-> new Exepcion("Resenia no encontrada"));
    }

    public Resenia getOne(String id){
        return reseniaRepositorio.getReferenceById(id);
    }

    @Override
    public void editarComentario(String id) throws Exepcion {
        String adminEdit = "COMENTARIO EDITADO POR UN ADMINISTRADOR";
        Resenia resenia = buscarById(id);
        resenia.setComentario(adminEdit);
        reseniaRepositorio.save(resenia);
    }

    private void validar (String comentario,String id) throws Exepcion {
        if (comentario.isEmpty()){
            throw new Exepcion("La celda comentario esta vacia");
        }
        if (id.isEmpty()){
            throw new Exepcion("No se encontro el usuario");
        }
    }


}
