package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Imagen;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.ImagenRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImagenServicio {

    ImagenRepositorio imagenRepositorio;

    public Imagen guardar(MultipartFile archivo) {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public Imagen modificar(MultipartFile archivo, String id) throws Exepcion, IOException {
        if (archivo != null) {
                Imagen imagen = buscarById(id);
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);
        }
        return null;
    }

    private Imagen buscarById(String id) throws Exepcion {

        Optional<Imagen> imagen = imagenRepositorio.findById(id);

        return imagen.orElseThrow(()-> new Exepcion("imagen no existe"));
    }


}
