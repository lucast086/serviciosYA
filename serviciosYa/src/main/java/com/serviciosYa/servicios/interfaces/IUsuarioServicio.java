package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;

import java.util.List;

public interface IUsuarioServicio {
     void crear(String nombre, String apellido, String email, String telefono, String password, String password2, Rol rol) throws Exepcion;
     void modificarById (String id,String nombre, String apellido, String email, String telefono, String password, Rol rol) throws Exepcion;
     void eliminarById (String id) throws Exepcion;
     Usuario buscarByID(String id) throws Exepcion;
     Usuario buscarByEmail(String email)throws Exepcion;
     Usuario buscarByNombreAndApellido(String nombre, String apellido )throws Exepcion;
     Usuario getOne (String id);
     List<Usuario> listarUsuarios ();
}
