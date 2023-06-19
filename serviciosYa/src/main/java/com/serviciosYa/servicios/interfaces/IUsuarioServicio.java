package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;

import java.util.List;

public interface IUsuarioServicio {

    public void crear(String nombre, String apellido, String email, String telefono, String password, String password2,Rol rol) throws Exepcion;
    public void modificarById (String id,String nombre, String apellido, String email, String telefono, String password, Rol rol) throws Exepcion;
    public void eliminarById (String id) throws Exepcion;
    public Usuario buscarByID(String id) throws Exepcion;
    public Usuario buscarByEmail(String email)throws Exepcion;
    public Usuario buscarByNombreAndApedillo(String nombre, String apedillo )throws Exepcion;
    public Usuario getOne (String id);
    public List<Usuario> listarUsuario ();
}
