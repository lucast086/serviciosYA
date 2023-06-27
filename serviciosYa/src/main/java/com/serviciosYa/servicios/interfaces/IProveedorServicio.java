package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;

import java.util.List;
import java.util.Optional;

public interface IProveedorServicio {
    void crear(String nombre, String apellido, String email, String telefono, String password, String password2, String imagen, List<Oficio> oficios, Rol rol) throws Exepcion;

    Proveedor buscarByEmail(String email) throws Exepcion;
}
