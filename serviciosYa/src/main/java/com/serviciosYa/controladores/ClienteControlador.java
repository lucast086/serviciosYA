package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.IClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cliente")
public class ClienteControlador {

    IClienteServicio clienteServicio;

    @GetMapping("/registro")
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

    @GetMapping("/modificar/{id}")
    public String modificarClienteForm(@PathVariable String id,ModelMap model){
        model.put("cliente",clienteServicio.getOne(id));
        return "cliente_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificarCliente (@PathVariable String id,@RequestParam String nombre,@RequestParam String apellido, @RequestParam String direccion,@RequestParam String email,@RequestParam String telefono, @RequestParam String password, @RequestParam String password2, ModelMap model){

        try {
            clienteServicio.modificarById(id,nombre,apellido,email,telefono,direccion,password,password2);
            model.put("exito","Usuario modificado con exito!");
            return "redirect:../listar";

        }catch (Exepcion ex){

            model.put("error",ex.getMessage());
            return "cliente_modificar.html";

        }

    }

    @GetMapping ("/eliminar/{id}")
    public String eliminarClienteForm(@PathVariable String id, ModelMap model){
        model.put("cliente",clienteServicio.getOne(id));
        return "cliente_eliminar.html";
    }

    @PostMapping ("/eliminar/{id}")
    public String eliminarCliente(@PathVariable  String id, ModelMap model){
        try {
            clienteServicio.eliminarById(id);
            model.put("exito","Se elimino con exito!");
            return "redirect:../listar";

        }catch (Exepcion ex){
            model.put("error",ex.getMessage());
            return "cliente_eleminar.htmlgit ";
        }
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable String id, ModelMap model){
        model.put("cliente",clienteServicio.getOne(id));
        return "cliente.html";
    }

    @GetMapping("/listar")
    public String listar (ModelMap model){
        List<Cliente> clienteList = clienteServicio.listarClientes();
        model.addAttribute("clientes",clienteList);
        return "lista_cliente.html";
    }






}
