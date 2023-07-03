package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Imagen;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.expression.spel.ast.OpDivide;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/proveedor")
public class ProveedorControlador {

    IProveedorServicio proveedorServicio;

    @GetMapping("")
    public String registrarProveedor(){
        return "proveedor_registro.html";
    }

    @PostMapping("/registroproveedor")
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

    @GetMapping("/modificar/{id}")
    public String modificarProveedorForm (@PathVariable String id, ModelMap model){
        model.put("proveedor",proveedorServicio.getOne(id));
        return "proveedor_moficar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificarProveedor (@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String telefono, @RequestParam String password, @RequestParam String password2, @RequestParam MultipartFile imagen, @RequestParam (value = "oficios",required = false)List<Oficio> oficios, ModelMap model){

        try {
            proveedorServicio.modificarByID(id, nombre, apellido, email, telefono, password, password2, imagen, oficios);
            model.put("exito","El proveedor se modifico con exito!");
            return "redirect:../listar";

        }catch (Exepcion e){
            model.put("error",e.getMessage());
            return"proveedor_modificar.html";

        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminrProveedorForm(@PathVariable String id, ModelMap model){
        model.put("proveedor",proveedorServicio.getOne(id));
        return "proveedor_eleminar.html";
    }


    public String eliminraProveedor(@PathVariable String id, ModelMap model){

        try {
            proveedorServicio.eliminarById(id);
            model.put("exito","El proveedor fue eliminado con exito!");
            return "redirect:../listar";
        }catch (Exepcion e){
            model.put("error",e.getMessage());
            return "proveedor_eleminar.html";
        }
    }

    @GetMapping("/listar")
    public String listarProveedor(ModelMap model){
        List<Proveedor> proveedorList = proveedorServicio.listarProveedores();
        model.addAttribute("proveedores",proveedorList);
        return "lista_proveedor.html";
    }

    @GetMapping("/{id}")
    public String getOne (@PathVariable String id, ModelMap model){
        model.put("proveedor",proveedorServicio.getOne(id));
        return "proveedor.html";
    }
}

