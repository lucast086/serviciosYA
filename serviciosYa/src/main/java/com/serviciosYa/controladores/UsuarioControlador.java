package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.IUsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class UsuarioControlador {

    IUsuarioServicio usuarioServicio;

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, ModelMap modelo){
        if (error != null ){
            modelo.put("error","Usuario o Contraseña Invalidos");
        }
        return "login.html";
    }

    @GetMapping("/usuarios")
    public String usuariosLogueados(){
        return "usuarios.html";
    }

    @GetMapping("/registrar")
    public String registrarForm(){
        return "intermedia.html";
    }

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



}

