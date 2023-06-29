package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Imagen;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/proveedor")
public class ProveedorControlador {

    IProveedorServicio proveedorServicio;

    @GetMapping("/registrar")
    public String registrarProveedor(){
        return "proveedor_registro.html";
    }

    @PostMapping("/registro")
    public String registro(RedirectAttributes redirectAttributes, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String telefono, @RequestParam String password, @RequestParam String password2, @RequestParam MultipartFile imagen, @RequestParam(value ="oficios", required = false) List<Oficio> oficios, ModelMap modelo) {
        try {
            proveedorServicio.crear(nombre,apellido,email,telefono,password,password2,imagen,oficios, Rol.PROVEEDOR);
            redirectAttributes.addFlashAttribute("exito", "Proveedor registrado correctamente!");
            return "redirect:/login";
        } catch (Exepcion ex) {
            redirectAttributes.addFlashAttribute("error",ex.getMessage());
            return "redirect:/proveedor";
        }
    }

}
