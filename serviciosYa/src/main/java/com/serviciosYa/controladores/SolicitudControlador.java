package com.serviciosYa.controladores;
import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.entidades.Solicitud;
import com.serviciosYa.enums.Estado;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.ProveedorServicio;
import com.serviciosYa.servicios.interfaces.IClienteServicio;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import com.serviciosYa.servicios.interfaces.ISolicitudServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/solicitud")
public class SolicitudControlador {

    ISolicitudServicio solicitudServicio;

    private IProveedorServicio proveedorServicio;
    private IClienteServicio clienteServicio;

    @GetMapping("/registro/{id}")
    public String registrarSolicitud(@PathVariable String id, ModelMap modelo) {

        Proveedor proveedor = proveedorServicio.getOne(id);
        modelo.put("proveedor", proveedor);

        return "solicitud.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/registrosolicitud")
    public String registrarSolicitud(RedirectAttributes redirectAttributes, @RequestParam String idCliente, @RequestParam String idProveedor, @RequestParam String descripcion, @RequestParam(required = false) Float costo, @RequestParam(required = false) String comentario, ModelMap modelo) {
        try {
            solicitudServicio.crearSolicitud(idCliente, idProveedor, descripcion, costo, comentario);
            redirectAttributes.addFlashAttribute("exito", "Solicitud registrada correctamente!");
            return "redirect:/solicitud/listar";
        } catch (Exepcion ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/usuarios";
        }
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable String id, ModelMap model) {
        model.put("solicitud", solicitudServicio.getOne(id));
        return "solicitud.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PROVEEDOR','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/listar")
    public String listarSolicitud(ModelMap model) {
        List<Solicitud> solicitudList = solicitudServicio.listarSolicitudes();
        model.addAttribute("solicitudes", solicitudList);
        return "listaSolicitudesCliente.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PROVEEDOR','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificarSolicitud(@PathVariable String id, ModelMap model) {
        model.put("solicitud", solicitudServicio.getOne(id));
        return "solicitud_modificar.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_PROVEEDOR','ROLE_ADMIN','ROLE_SUPERADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificarSolicitud(@PathVariable String id, @RequestParam Cliente cliente, @RequestParam Proveedor proveedor, @RequestParam String descripcion, @RequestParam Estado estado, @RequestParam Float costo, @RequestParam String comentario, ModelMap modelo) {

        try {
            List<Proveedor> proveedores = proveedorServicio.listarProveedores();
            modelo.addAttribute("proveedores", proveedores);

            solicitudServicio.modificarById(id, cliente, proveedor, descripcion, estado, costo, comentario);
            modelo.put("exito", "La solicitud se modifico con exito!");

            return "redirect:../listar";

        } catch (Exepcion e) {
            List<Proveedor> proveedores = proveedorServicio.listarProveedores();
            modelo.addAttribute("proveedores", proveedores);

            modelo.put("error", e.getMessage());
            return "solicitud_modificar.html";

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminarSolicitudForm (@PathVariable String id, ModelMap model){
        model.put("solicitud",solicitudServicio.getOne(id));
        return "solicitud_eliminar.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @PostMapping("/eliminar/{id}")
    public String eliminarSolicitud (@PathVariable String id, ModelMap model){

        try {
            solicitudServicio.eliminarById(id);
            model.put("exito","Solicitud eliminada");
            return "redirect:../listar";

        }catch (Exepcion e){
            model.put("error", e.getMessage());
            return "solicitud_eliminar.html";
        }
    }

}