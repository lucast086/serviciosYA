package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Resenia;
import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.enums.Estrella;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.OficioRepositorio;
import com.serviciosYa.repositorios.ReseniaRepositorio;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import com.serviciosYa.servicios.interfaces.IReseniaServicio;
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
    IUsuarioServicio usuarioServicio;
    @Transactional
    public Resenia crear(String comentario, Estrella estrella,String idProveedor) throws Exepcion {

        validar(comentario,estrella,idProveedor);

        Resenia resenia = new Resenia();

        Proveedor proveedor = proveedorServicio.getOne(idProveedor);

        resenia.setComentario(comentario);
        resenia.setEstrellas(estrella);
        resenia.setProveedor(proveedor);

        reseniaRepositorio.save(resenia);

        return resenia;

    }
    @Transactional
    public void eliminarById (String id) throws Exepcion {
        Resenia resenia = buscarById(id);
        reseniaRepositorio.delete(resenia);
    }
    @Transactional
    public void modificarById (String id, String comentario, Estrella estrella,Proveedor proveedor) throws Exepcion {
        Resenia resenia = buscarById(id);

        resenia.setComentario(comentario);
        resenia.setEstrellas(estrella);
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

    private void validar (String comentario,Estrella estrella, String id) throws Exepcion {
        if (comentario.isEmpty()){
            throw new Exepcion("La celda comentario esta vacia");
        }
        if (estrella != Estrella.UNO && estrella != Estrella.DOS && estrella != Estrella.TRES && estrella != Estrella.CUATRO && estrella != Estrella.CINCO ){
            throw new Exepcion("Estrellas incorrectas");
        }
        if (id.isEmpty()){
            throw new Exepcion("No se encontro el usuario");
        }
    }


}
