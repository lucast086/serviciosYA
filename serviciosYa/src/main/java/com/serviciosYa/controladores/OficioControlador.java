package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.OficioServicio;
import com.serviciosYa.servicios.interfaces.IOficioServicio;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/oficio")
public class OficioControlador {

    IOficioServicio oficioServicio;

    @GetMapping("/registro")
    public String registroOficio(){
        return "crearOficio.html";
    }

    @PostMapping("/registro")
    public String registro(RedirectAttributes redirectAttributes, @RequestParam String nombre, @RequestParam String descripcion,@RequestParam String codigo, @RequestParam String color, ModelMap model){
        try{
        oficioServicio.crearOficio(nombre,descripcion,codigo,color);
        redirectAttributes.addFlashAttribute("exito","Oficio registrado correctamente!");
        return "redirect:/";
        } catch (Exepcion e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/oficio/registro";
        }
    }


    @GetMapping("/modificar/{id}")
    public String modificarOficioForm (@PathVariable String id, ModelMap model){
        model.put("oficio",oficioServicio.getOne(id));
        return "oficio_modifcar.html" ;
    }

    @PostMapping("/modificar/{id}")
    public String modificarOficio (@PathVariable String id,@RequestParam String nombre,@RequestParam String descripcion,@RequestParam String codigo,@RequestParam String color, ModelMap model){
        try{
            oficioServicio.modificarById(id, nombre, descripcion, codigo, color);
            model.put("exito", "El Oficio se modifico con exito");
            return "redirect:../listar";
        } catch (Exepcion e) {
            model.put("oficio",oficioServicio.getOne(id));
            model.put("error", e.getMessage()) ;
            return "oficio_modificar";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarOficioForm(@PathVariable String id, ModelMap model){
        model.put("oficio",oficioServicio.getOne(id));
        return "oficio_eliminar";
    }

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

    @GetMapping("/{id}")
    public String getOne (@PathVariable String id, ModelMap model){
        model.put("oficio",oficioServicio.getOne(id));
        return "oficio.html";
}
    @GetMapping("/listar")
    public String listar (ModelMap model){
        List<Oficio>oficioList=oficioServicio.listarTodos();
        model.put("oficios",oficioList);
        return "listaOficio.html";
    }
}

