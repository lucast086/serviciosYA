package com.serviciosYa.controladores;

import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.servicios.OficioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/oficio")
public class OficioControlador {

    private OficioServicio oficioServicio;

    // FALTA el de crear y eliminar oficios
}
