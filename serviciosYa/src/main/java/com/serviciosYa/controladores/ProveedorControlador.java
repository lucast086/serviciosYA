package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.IOficioServicio;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import com.serviciosYa.servicios.interfaces.IReseniaServicio;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/proveedor")
public class ProveedorControlador {

    IProveedorServicio proveedorServicio;
    IOficioServicio oficioServicio;
    IReseniaServicio reseniaServicio;

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR')")
    @GetMapping("/dashboard")
    public String dashboard(ModelMap model) {
        return "usuarios.html";
    }

    @GetMapping("/registro")
    public String registrarProveedor(ModelMap model){
        model.addAttribute("oficiosList",oficioServicio.listarTodos());
        return "proveedorRegistro.html";
    }

    @PostMapping("/calificar/{id}")
    public String registrarProveedor(RedirectAttributes redirectAttributes, @PathVariable String id,@RequestParam String idSolicitud, @RequestParam String comentario, @RequestParam String estrellas, ModelMap model){
        try {
            reseniaServicio.crear(comentario, estrellas, id, idSolicitud);
            redirectAttributes.addFlashAttribute("exito","El proveedor fue calificado con exito!");
            return "redirect:/usuarios";
        } catch (Exepcion e) {
            redirectAttributes.addFlashAttribute("error","El proveedor NO fue calificado con exito!");
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/registro")
    public String registro(RedirectAttributes redirectAttributes,@RequestParam(required = false) List<String> oficiosSeleccionados, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String telefono, @RequestParam String password, @RequestParam String password2, @RequestParam MultipartFile imagen, ModelMap modelo) {
        try {
            List<Oficio> oficios = oficioServicio.listarTodos(oficiosSeleccionados);
            proveedorServicio.crear(nombre,apellido,email,telefono,password,password2,imagen,oficios, Rol.PROVEEDOR);
            redirectAttributes.addFlashAttribute("exito", "Proveedor registrado correctamente!");
            return "redirect:/login";
        } catch (Exepcion ex) {
            redirectAttributes.addFlashAttribute("error",ex.getMessage());
            return "redirect:/proveedor/registro";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificarProveedorForm (@PathVariable String id, ModelMap model){
        model.put("proveedor",proveedorServicio.getOne(id));
        model.addAttribute("oficiosList",oficioServicio.listarTodos());
        return "modificarProveedor.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificarProveedor (@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String telefono, @RequestParam String password, @RequestParam String password2, @RequestParam MultipartFile imagen, @RequestParam (value = "oficios",required = false)List<Oficio> oficios,  ModelMap model){

        try {
            proveedorServicio.modificarByID(id, nombre, apellido, email, telefono, password, password2, imagen, oficios);
            model.put("exito","El proveedor se modifico con exito!");
            return "usuarios.html";

        }catch (Exepcion e){
            model.put("proveedor",proveedorServicio.getOne(id));
            model.put("error",e.getMessage());
            return"modificarProveedor.html";
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminraProveedor(@PathVariable String id, ModelMap model){
        try {
            proveedorServicio.eliminarById(id);
            model.put("exito","El proveedor fue eliminado con exito!");
            return "redirect:/proveedor/listar";
        }catch (Exepcion e){
            model.put("error",e.getMessage());
            return "redirect:/proveedor/listar";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/listar")
    public String listarProveedor(ModelMap model){
        List<Proveedor> proveedorList = proveedorServicio.listarProveedores();
        model.addAttribute("proveedores",proveedorList);
        return "listaProveedor.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/listar/{id}")
    public String listarProveedorPorOficio(@PathVariable String id, ModelMap model){
        List<Proveedor> proveedorList = proveedorServicio.listarProveedoresPorOficio(id);
        model.addAttribute("proveedores",proveedorList);
        return "tarjetas.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PROVEEDOR','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/perfil/{id}")
    public String getOne (@PathVariable String id, ModelMap model){
        Proveedor proveedor = proveedorServicio.getOne(id);
        //Float promEstrellas = proveedorServicio.calcularEstrellas(proveedor);
        //model.put("estrellas", promEstrellas);
        model.put("proveedor",proveedor);
        model.addAttribute("oficios", proveedor.getOficios());
        return "vista_perfil_proveedor.html";
    }

}


