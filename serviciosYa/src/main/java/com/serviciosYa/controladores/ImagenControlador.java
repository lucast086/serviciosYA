package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/imagen")
public class ImagenControlador {

    IProveedorServicio proveedorServicio;

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<byte[]> imagenProveedor(@PathVariable String id){
        Proveedor proveedor = proveedorServicio.getOne(id);
        byte[] imagen = proveedor.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
}
