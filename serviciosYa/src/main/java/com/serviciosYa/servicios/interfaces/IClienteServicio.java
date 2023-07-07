package com.serviciosYa.servicios.interfaces;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Solicitud;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;

import java.util.List;

public interface IClienteServicio {

     void crear(String nombre, String apellido, String email, String telefono, String direccion, String password, String password2,Rol rol,List<Solicitud> solicitudes) throws Exepcion;
     void modificarById (String id,String nombre, String apellido, String email, String telefono,String direccion, String password,String password2,List<Solicitud> solicitudes) throws Exepcion;
     void eliminarById (String id) throws Exepcion;
     Cliente buscarByID(String id) throws Exepcion;
     Cliente buscarByEmail(String email)throws Exepcion;
     Cliente buscarByNombreAndApedillo(String nombre, String apedillo )throws Exepcion;
     Cliente getOne (String id);
     List<Cliente> listarClientes ();

}
