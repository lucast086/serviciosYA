package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.IClienteServicio;
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
@RequestMapping("/cliente")
public class ClienteControlador {

    IClienteServicio clienteServicio;
    IOficioServicio oficioServicio;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/dashboard")
    public String dashboard(ModelMap model) {
        List<Oficio>oficioList=oficioServicio.listarTodos();
        model.addAttribute("oficiosList",oficioList);
        return "usuarios.html";
    }

    @GetMapping("/registro")
    public String registrarCliente(){
        return "clienteRegistro.html";
    }

    @PostMapping("/registro")
    public String registro(RedirectAttributes redirectAttributes, @RequestParam String nombre,@RequestParam String apellido,@RequestParam String direccion,@RequestParam String email,@RequestParam String telefono,@RequestParam String password,@RequestParam String password2, ModelMap modelo) {
        try {
            clienteServicio.crear(nombre,apellido,direccion,email,telefono,password,password2,Rol.USER);
            redirectAttributes.addFlashAttribute("exito", "Cliente registrado correctamente!");
            return "redirect:/login";
        } catch (Exepcion ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/cliente/registro";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificarClienteForm(@PathVariable String id,ModelMap model){
        model.put("cliente",clienteServicio.getOne(id));
        return "modificarCliente.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificarCliente (@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String direccion, @RequestParam String email, @RequestParam String telefono, @RequestParam String password, @RequestParam String password2, ModelMap model){

        try {
            clienteServicio.modificarById(id,nombre,apellido,email,telefono,direccion,password,password2);
            model.put("exito","Usuario modificado con exito!");
            return "usuarios.html";

        }catch (Exepcion ex){
            model.put("cliente",clienteServicio.getOne(id));
            model.put("error",ex.getMessage());
            return "modificarCliente.html";

        }

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping ("/eliminar/{id}")
    public String eliminarCliente(@PathVariable  String id, ModelMap model){
        try {
            clienteServicio.eliminarById(id);
            model.put("exito","Se elimino con exito!");

        }catch (Exepcion ex){
            model.put("error",ex.getMessage());
        }
        return "redirect:/cliente/listar";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PROVEEDOR','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/perfil/{id}")
    public String getOne(@PathVariable String id, ModelMap model){
        model.put("cliente",clienteServicio.getOne(id));
        return "vista_perfil_cliente.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/listar")
    public String listar (ModelMap model){
        List<Cliente> clienteList = clienteServicio.listarClientes();
        model.addAttribute("clientes",clienteList);
        return "listaCliente.html";
    }

}
