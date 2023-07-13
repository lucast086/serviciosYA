package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.IOficioServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/oficio")
public class OficioControlador {

    IOficioServicio oficioServicio;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/registro")
    public String registroOficio(){
        return "crearOficio.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @PostMapping("/registro")
    public String registro(RedirectAttributes redirectAttributes, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam String codigo, @RequestParam Boolean color, ModelMap model){
        try{
        oficioServicio.crearOficio(nombre,descripcion,codigo,color);
        redirectAttributes.addFlashAttribute("exito","Oficio registrado correctamente!");
        return "redirect:/";
        } catch (Exepcion e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/oficio/registro";
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificarOficioForm (@PathVariable String id, ModelMap model){
        model.put("oficio",oficioServicio.getOne(id));
        return "modificarOficio.html" ;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificarOficio (@PathVariable String id,@RequestParam String nombre,@RequestParam String descripcion,@RequestParam String codigo,@RequestParam Boolean color, ModelMap model){
        try{
            oficioServicio.modificarById(id, nombre, descripcion, codigo, color);
            model.put("exito", "El Oficio se modifico con exito");
            return "usuarios.html";
        } catch (Exepcion e) {
            model.put("oficio",oficioServicio.getOne(id));
            model.put("error", e.getMessage()) ;
            return "modificarOficio";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminarOficioForm(@PathVariable String id, ModelMap model){
        model.put("oficio",oficioServicio.getOne(id));
        return "oficio_eliminar";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @PostMapping("/eliminar/{id}")
    public String eliminarOficio (@PathVariable String id, ModelMap model){
        try{
            oficioServicio.eliminarById(id);
            model.put("exito","El Oficio fue eliminado con exito!");
            return "redirect:../listar";
        } catch (Exepcion e) {
            model.put("error",e.getMessage());
            return "oficio_eleminar.html";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PROVEEDOR','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/{id}")
    public String getOne (@PathVariable String id, ModelMap model){
        model.put("oficio",oficioServicio.getOne(id));
        return "oficio.html";
}

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PROVEEDOR','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/listar")
    public String listar (ModelMap model){
        List<Oficio>oficioList=oficioServicio.listarTodos();
        model.put("oficios",oficioList);
        return "listaOficio.html";
    }
}

