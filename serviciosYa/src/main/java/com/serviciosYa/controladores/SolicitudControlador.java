package com.serviciosYa.controladores;
import com.serviciosYa.servicios.interfaces.ISolicitudServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/solicitud")
public class SolicitudControlador {

    ISolicitudServicio solicitudServicio;

    @GetMapping("")
    public String registrarSolicitud(){
        return "solicitud_registro.html";
    }

   // @PostMapping("/registrosolicitud")



}
