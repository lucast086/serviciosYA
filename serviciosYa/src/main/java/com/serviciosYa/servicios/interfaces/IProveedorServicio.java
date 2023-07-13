package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.*;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IProveedorServicio {
    void crear(String nombre, String apellido, String email, String telefono, String password, String password2, MultipartFile imagen, List<Oficio> oficios, Rol rol) throws Exepcion;

    void modificarByID(String id,String nombre, String apellido, String email, String telefono, String password, String password2, MultipartFile imagen, List<Oficio> oficios) throws Exepcion;

    Proveedor buscarByID(String id) throws Exepcion;

    Proveedor buscarByEmail(String email) throws Exepcion;

    void eliminarById (String id) throws Exepcion;
    Proveedor buscarByNombreAndApellido(String nombre, String apellido )throws Exepcion;
    Proveedor getOne (String id);
    List<Proveedor> listarProveedores ();

    List<Proveedor> listarProveedoresPorOficio(String oficioId);

    Float calcularEstrellas(Proveedor proveedor);
}
