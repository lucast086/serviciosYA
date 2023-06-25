package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/registrar/proveedor")
public class ProveedorControlador {

    IProveedorServicio proveedorServicio;

    @GetMapping("/")
    public String registrarProveedor(){
        return "proveedor_registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String telefono, @RequestParam String password, @RequestParam String password2, @RequestParam String imagen, @RequestParam List<Oficio> oficios, ModelMap modelo) {
        try {
            proveedorServicio.crear(nombre,apellido,email,telefono,password,password2,imagen,oficios, Rol.PROVEEDOR);
            modelo.put("exito", "Proveedor registrado correctamente!");
            return "login.html";
        } catch (Exepcion ex) {
            modelo.put("error",ex.getMessage());
            return "proveedor_registro.html";
        }
    }

}
