package com.serviciosYa.controladores;
import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.enums.Estado;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.ISolicitudServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/solicitud")
public class SolicitudControlador {

    ISolicitudServicio solicitudServicio;

    @GetMapping("")
    public String registrarSolicitud(){
        return "solicitud_registro.html";
    }

   @PostMapping("/registrosolicitud")
    public String registroSolicitud(RedirectAttributes redirectAttributes, @RequestParam Cliente cliente, @RequestParam List<Proveedor>proveedores, @RequestParam String descripcion, @RequestParam Estado estado, @RequestParam float costo, @RequestParam String comentario, @RequestParam Date fechaServicio, ModelMap modelo){
       try {
           solicitudServicio.crearSolicitud(cliente,proveedores,descripcion,estado,costo,comentario, fechaServicio);
           redirectAttributes.addFlashAttribute("exito", "Solicitud registrada correctamente!");
           return "cliente.html";
       } catch (Exepcion ex) {
           redirectAttributes.addFlashAttribute("error",ex.getMessage());
           return "solicitud_registro.html";
       }


    }



}
