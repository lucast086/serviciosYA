package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.servicios.OficioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class OficioControlador {

    private OficioServicio oficioServicio;

    @GetMapping("/")
    public String index(ModelMap modelo) {

        List<Oficio> oficios = oficioServicio.listarTodos();
        modelo.addAttribute("oficios", oficios);

        return "index.html";
    }

    

}
