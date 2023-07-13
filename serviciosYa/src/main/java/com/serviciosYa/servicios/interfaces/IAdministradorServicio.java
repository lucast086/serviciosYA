package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.Administrador;
import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;

import java.util.List;

public interface IAdministradorServicio {

    void crear(String nombre, String apellido, String email, String telefono, String password, String password2, Rol rol) throws Exepcion;
    void modificarById (String id,String nombre, String apellido, String telefono, String password, String password2) throws Exepcion;
    void eliminarById (String id) throws Exepcion;
    Administrador buscarByID(String id) throws Exepcion;
    Administrador buscarByEmail(String email)throws Exepcion;
    Administrador buscarByNombreAndApedillo(String nombre, String apedillo )throws Exepcion;
    Administrador getOne (String id);
    List<Administrador> listarAdministradores ();
}
