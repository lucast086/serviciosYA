package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.repositorios.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServicio {

    private UsuarioRepositorio ur;
    @Transactional
    public void crear(String nombre, String apedillo, String email, String telefono, String password,String password2, Boolean activo) throws Exception {

        validar(nombre,apedillo,email,telefono,password,password2);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellido(apedillo);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);
        usuario.setRol(Rol.USER);
        usuario.setActivo(true);

        ur.save(usuario);

    }
    @Transactional
    public void modificarByEmail (String nombre, String apedillo, String email, String telefono, String password,String password2, Boolean activo) throws Exception {

        Usuario usuario = buscarByEmail(email);

        validar(nombre,apedillo,email,telefono,password,password2);

        usuario.setNombre(nombre);
        usuario.setApellido(apedillo);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);
        usuario.setRol(Rol.USER);
        usuario.setActivo(true);
        ur.save(usuario);

    }
    @Transactional
    public void modificarByNombreAndApedillo (String nombre, String apedillo, String email, String telefono, String password,String password2, Boolean activo) throws Exception {

        Usuario usuario = buscarByNombreAndApedillo(apedillo,nombre);

        validar(nombre,apedillo,email,telefono,password,password2);

        usuario.setNombre(nombre);
        usuario.setApellido(apedillo);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);
        usuario.setRol(Rol.USER);
        usuario.setActivo(true);
        ur.save(usuario);

    }
    @Transactional
    public void modificarById (String id,String nombre, String apedillo, String email, String telefono, String password,String password2, Boolean activo) throws Exception {

        Usuario usuario = buscarByID(id);

        validar(nombre,apedillo,email,telefono,password,password2);

        usuario.setNombre(nombre);
        usuario.setApellido(apedillo);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);
        usuario.setRol(Rol.USER);
        usuario.setActivo(true);
        ur.save(usuario);

    }

    public void eliminarById (String id){
        boolean confirmar = false;

        Usuario usuario = buscarByID(id);

        if (confirmar == true){
            ur.delete(usuario);
        }

    }

    public void eliminarByEmail (String email){
        boolean confirmar = false;

        Usuario usuario = buscarByEmail(email);

        if (confirmar == true){
            ur.delete(usuario);
        }

    }

    public void eliminarByNombreAndApedillo (String nombre, String apedillo){
        boolean confirmar = false;

        Usuario usuario = buscarByNombreAndApedillo(nombre,apedillo);

        if (confirmar == true){
            ur.delete(usuario);
        }

    }


    public Usuario buscarByID(String id){

        Optional<Usuario> repuesta = ur.findById(id);

        Usuario usuario = new Usuario();

        if (repuesta.isPresent()){
            usuario = repuesta.get();
        }

        return usuario;
    }


    public Usuario buscarByEmail(String email){

        Optional<Usuario> repuesta = ur.findByEmail(email);

        Usuario usuario = new Usuario();

        if (repuesta.isPresent()){
           usuario = repuesta.get();
        }

        return usuario;
    }

    public Usuario buscarByNombreAndApedillo(String nombre, String apedillo ){

        Optional<Usuario> repuesta = ur.findByNombreAndApellido(nombre,apedillo);

        Usuario usuario = new Usuario();

        if (repuesta.isPresent()){
            usuario = repuesta.get();
        }

        return usuario;
    }

    private void validar (String nombre, String apedillo, String email, String telefono, String password, String password2) throws Exception{

        //Mensajes de error sujeridos.

        if (nombre.isEmpty()){
         throw new Exception("La celda nombre esta vacia");
        }

        if (apedillo.isEmpty()){
            throw new Exception("La celda apedillo esta vacia");
        }

        if (email.isEmpty()){
            throw new Exception("La celda email esta vacia");
        }

        if (telefono.isEmpty()){
            throw new Exception("La celda telofono esta vacia");
        }

        if (password.isEmpty() || password.length()<=6){
            throw new Exception("La celda contraseña esta vacia");
        }

        if (password2 == password){
            throw new Exception("Las contraseñas no son iguales");
        }

    }


}
