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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/cliente")
public class ClienteControlador {

    IClienteServicio clienteServicio;

    @GetMapping("")
    public String registrarCliente(){
        return "cliente_registro.html";
    }

    @PostMapping("/registrocliente")
    public String registro(RedirectAttributes redirectAttributes, @RequestParam String nombre,@RequestParam String apellido,@RequestParam String direccion,@RequestParam String email,@RequestParam String telefono,@RequestParam String password,@RequestParam String password2, ModelMap modelo) {
        try {
            clienteServicio.crear(nombre,apellido,direccion,email,telefono,password,password2,Rol.USER);
            redirectAttributes.addFlashAttribute("exito", "Cliente registrado correctamente!");
            return "redirect:/login";
        } catch (Exepcion ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/cliente";
        }
    }
}
