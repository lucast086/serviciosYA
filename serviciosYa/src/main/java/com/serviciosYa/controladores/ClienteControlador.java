package com.serviciosYa.controladores;

import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.IClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/registrar/cliente")
public class ClienteControlador {

    IClienteServicio clienteServicio;

    @GetMapping("/")
    public String registrarCliente(){
        return "cliente_registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String direccion,@RequestParam String email,@RequestParam String telefono,@RequestParam String password,@RequestParam String password2, ModelMap modelo) {
        try {
            clienteServicio.crear(nombre,apellido,direccion,email,telefono,password,password2,Rol.USER);
            modelo.put("exito", "Cliente registrado correctamente!");
            return "login.html";
        } catch (Exepcion ex) {
            modelo.put("error",ex.getMessage());
            return "cliente_registro.html";
        }
    }
}
