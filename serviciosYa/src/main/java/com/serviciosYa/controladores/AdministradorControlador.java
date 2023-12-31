package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Administrador;
import com.serviciosYa.entidades.Resenia;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.ClienteServicio;
import com.serviciosYa.servicios.interfaces.IAdministradorServicio;
import com.serviciosYa.servicios.interfaces.IReseniaServicio;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdministradorControlador {

    IAdministradorServicio administradorServicio;
    ClienteServicio clienteServicio;
    IReseniaServicio reseniaServicio;


    @GetMapping("/generar")
    public String generarAdmin(ModelMap model){
        try {
            administradorServicio.crear("admin","admin","admin@admin.com","888888","7654321","7654321",Rol.ADMIN);
            model.put("exito","admin registrado");
            return "index.html";
        } catch (Exepcion ex ) {
            model.put("error", ex.getMessage());
            return "admin_registro.html";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/perfil/{id}")
    public String getOne(@PathVariable String id, ModelMap model){
        model.put("admin",administradorServicio.getOne(id));
        return "vista_perfil_admin.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/dashboard")
    public String dashboard(ModelMap model) {
        return "usuarios.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
    @GetMapping("/registro")
    public String registrarForm(){
        return "admin_registro.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
    @PostMapping("/registro")
    public String registrar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String telefono, @RequestParam String password, @RequestParam String password2, @RequestParam Rol rol , ModelMap model) {
        try {
            administradorServicio.crear(nombre,apellido,email,telefono,password,password2,rol);
            model.put("exito","admin registrado");
        } catch (Exepcion ex ) {
            model.put("error", ex.getMessage());
            return "admin_registro.html";
        }
        return "index.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping ("/modificar/{id}")
    public String modificarForm (@PathVariable String id, ModelMap model){

        model.put("admin", administradorServicio.getOne(id));

        return "modificarAdmin.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificar (@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido , @RequestParam String telefono,@RequestParam String password, @RequestParam String password2, ModelMap model ){
        try {
            administradorServicio.modificarById(id,nombre,apellido,telefono,password,password2);

            return "redirect:../listar";
        }catch (Exepcion ex){
            model.put("admin", administradorServicio.getOne(id));
            model.put("error", ex.getMessage());
            return "modificarAdmin.html";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping ("/comentarios")
    public String comentarios (ModelMap model){

        List<Resenia> resenias = reseniaServicio.lsitarResenia();
        model.addAttribute("resenias",resenias);

        return "listaComentarios.html";
    }

    @GetMapping("/eliminarResenia/{id}")
    public String eliminarResenia(@PathVariable String id, ModelMap model){
        try {
            reseniaServicio.editarComentario(id);
            model.put("exito","Resenia Editada con exito");
            return "redirect:/admin/comentarios";
        } catch (Exepcion e){
            model.put("error",e.getMessage());
            return "redirect:/admin/comentarios";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
    @GetMapping ("/listar")
    public String listar (ModelMap model){

        List<Administrador> adminList = administradorServicio.listarAdministradores();
        model.addAttribute("administradores",adminList);

        return "admin_lista.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminarForm (@PathVariable String id,ModelMap model){

        model.put("admin", administradorServicio.getOne(id));

        return "admin_eliminar.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
    @PostMapping("/eliminar/{id}")
    public String eliminar (@PathVariable String id, ModelMap model){

        try {
            administradorServicio.eliminarById(id);
        } catch (Exepcion ex) {
            model.put("error", ex.getMessage());
            return "admin_eliminar.html";
        }

        return "index.html";
    }
    @GetMapping("/cambiarRol/{id}")
    public String cambiarRol (@PathVariable String id, ModelMap model) {

        try {
            clienteServicio.cambiarRol(id);
        } catch (Exepcion ex) {
            model.put("error", ex.getMessage());

            return "listaCliente.html";
        }
        return "listaCliente.html";
    }

}
