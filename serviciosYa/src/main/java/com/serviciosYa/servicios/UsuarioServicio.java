package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.UsuarioRepositorio;
import com.serviciosYa.servicios.interfaces.IUsuarioServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    private UsuarioRepositorio usuarioRepositorio;
    @Transactional
    public void crear(String nombre, String apellido, String email, String telefono, String password,String password2,Rol rol) throws Exepcion {

        validar(nombre,apellido,email,telefono,password,rol);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);
        usuario.setRol(rol);
        usuario.setActivo(true);
        usuarioRepositorio.save(usuario);

    }

    @Transactional
    public void modificarById (String id,String nombre, String apellido, String email, String telefono, String password, Rol rol) throws Exepcion {

        Usuario usuario = buscarByID(id);

        validar(nombre,apellido,email,telefono,password,rol);

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setRol(rol);
        usuarioRepositorio.save(usuario);

    }

    @Transactional
    public void eliminarById (String id) throws Exepcion {

        Usuario usuario = buscarByID(id);
        usuario.setActivo(false);
        usuarioRepositorio.save(usuario);

    }

    public Usuario buscarByID(String id)throws Exepcion{

        Optional<Usuario> repuesta = usuarioRepositorio.findById(id);

        return repuesta.orElseThrow(()-> new Exepcion("Usuario no existe"));
    }


    public Usuario buscarByEmail(String email) throws Exepcion{

        Optional<Usuario> repuesta = usuarioRepositorio.findByEmail(email);


        return repuesta.orElseThrow(()-> new Exepcion("Usuario no existe"));

    }

    public Usuario buscarByNombreAndApedillo(String nombre, String apellido )throws Exepcion{

        Optional<Usuario> repuesta = usuarioRepositorio.findByNombreAndApellido(nombre,apellido);

        return repuesta.orElseThrow(()-> new Exepcion("Usuario no existe"));
    }

    public Usuario getOne (String id){
        return usuarioRepositorio.getOne(id);
    }

    public List<Usuario> listarUsuario (){
        List<Usuario> usuario = new ArrayList(usuarioRepositorio.findAll());
        return usuario;
    }

    private void validar (String nombre, String apellido, String email, String telefono, String password,Rol rol) throws Exepcion{


        if (nombre.isEmpty()){
         throw new Exepcion("La celda nombre esta vacia");
        }

        if (apellido.isEmpty()){
            throw new Exepcion("La celda apedillo esta vacia");
        }

        if (email.isEmpty()){
            throw new Exepcion("La celda email esta vacia");
        }

        if (telefono.isEmpty()){
            throw new Exepcion("La celda telofono esta vacia");
        }

        if (password.isEmpty() || password.length()<=6){
            throw new Exepcion("La celda contraseña esta vacia");
        }

        if(rol != Rol.ADMIN && rol!=Rol.USER && rol != Rol.PROVEEDOR){
            throw new Exepcion( "Rol incorrecto");
        }

    }


}
