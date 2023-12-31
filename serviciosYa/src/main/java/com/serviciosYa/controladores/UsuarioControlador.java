package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.OficioServicio;
import com.serviciosYa.servicios.interfaces.IUsuarioServicio;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class UsuarioControlador {

    private IUsuarioServicio usuarioServicio;
    private OficioServicio oficioServicio;

    @GetMapping("/")
    public String listarTodos(ModelMap modelo) {

        List<Oficio> oficios = oficioServicio.listarTodos();
        modelo.addAttribute("oficiosList", oficios);
        log.warn(oficios.toString());
        return "index.html";
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, ModelMap modelo){
        if (error != null ){
            modelo.put("error","Usuario o Contraseña Invalidos");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_USER','ROLE_PROVEEDOR','ROLE_ADMIN')")
    @GetMapping("/usuarios")
    public String usuariosLogueados(RedirectAttributes redirectAttributes, HttpSession session){
        Usuario logueado = (Usuario) session.getAttribute("usuarioSesion");
        String rol = logueado.getRol().toString();

        switch (rol) {
            case "PROVEEDOR" :
                  return  "redirect:/proveedor/dashboard";
            case "ADMIN" :
                 return  "redirect:/admin/dashboard";
            case "SUPERADMIN" :
                  return  "redirect:/admin/dashboard";
            case "USER" :
                return  "redirect:/cliente/dashboard";
        }
        return "usuarios.html";
    }

    @GetMapping("/registrar")
    public String registrarForm(){
        return "intermedia.html";
    }
/*
    @PostMapping("/registro")
    public String registrar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String telefono, @RequestParam String password, @RequestParam String password2, @RequestParam Rol rol , ModelMap model) {
        try {
            usuarioServicio.crear(nombre,apellido,email,telefono,password,password2,rol);
            model.put("exito","usuario registrado");
        } catch (Exepcion ex ) {
         model.put("error", ex.getMessage());
         return "usuario_registro.html";
        }
        return "index.html";
    }

    @GetMapping ("/modificar/{id}")
    public String modificarForm (@PathVariable String id, ModelMap model){

        model.put("usuario",usuarioServicio.getOne(id));

        return "usuario_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar (@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido , @RequestParam String email, @RequestParam String telefono,@RequestParam String password, @RequestParam Rol rol, ModelMap model ){
        try {
            usuarioServicio.modificarById(id,nombre,apellido,email,telefono,password,rol );

            return "redirect:../listar";
        }catch (Exepcion ex){
            model.put("error", ex.getMessage());
            return "usuario_modificar.html";
        }
    }

    @GetMapping ("/listar")
    public String listar (ModelMap model){

        List<Usuario> usuarioList = usuarioServicio.listarUsuarios();
        model.addAttribute("usuario",usuarioList);

        return "usuario_lista.html";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarForm (@PathVariable String id,ModelMap model){

        model.put("usuario",usuarioServicio.getOne(id));

        return "usuario_eliminar.html";
    }


    @PostMapping("/eliminar/{id}")
    public String eliminar (@PathVariable String id, ModelMap model){

        try {
            usuarioServicio.eliminarById(id);
        } catch (Exepcion ex) {
            model.put("error", ex.getMessage());
            return "usuario_eliminar.html";
        }

        return "index.html";
    }

*/

}

