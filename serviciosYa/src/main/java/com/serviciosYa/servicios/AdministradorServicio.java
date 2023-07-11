package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Administrador;
import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.AdministradorRepositorio;
import com.serviciosYa.servicios.interfaces.IAdministradorServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdministradorServicio implements IAdministradorServicio {

    private AdministradorRepositorio administradorRepositorio;
    @Transactional
    public void crear(String nombre, String apellido, String email, String telefono, String password, String password2, Rol rol) throws Exepcion {

        Optional<Administrador> respuesta = administradorRepositorio.findByEmail(email);
        if (respuesta.isPresent()) {
            throw new Exepcion("el email ya esta registrado");
        }

        validar(nombre,apellido,email,telefono,password);

        Administrador administrador = new Administrador();

        administrador.setNombre(nombre);
        administrador.setApellido(apellido);
        administrador.setEmail(email);
        administrador.setTelefono(telefono);
        administrador.setPassword(
                new BCryptPasswordEncoder().encode(password)
        );
        administrador.setRol(rol);
        administrador.setActivo(true);
        administradorRepositorio.save(administrador);

    }

    @Transactional
    public void modificarById (String id,String nombre, String apellido, String email, String telefono, String password) throws Exepcion {

        Administrador administrador = buscarByID(id);

        validar(nombre,apellido,email,telefono,password);

        administrador.setNombre(nombre);
        administrador.setApellido(apellido);
        administrador.setEmail(email);
        administrador.setTelefono(telefono);
        administradorRepositorio.save(administrador);

    }

    @Transactional
    public void eliminarById (String id) throws Exepcion {

        Administrador administrador = buscarByID(id);
        administrador.setActivo(false);
        administradorRepositorio.save(administrador);

    }

    public Administrador buscarByID(String id)throws Exepcion{

        Optional<Administrador> respuesta = administradorRepositorio.findById(id);

        return respuesta.orElseThrow(()-> new Exepcion("Administrador no existe"));
    }


    public Administrador buscarByEmail(String email) throws Exepcion{

        Optional<Administrador> respuesta = administradorRepositorio.findByEmail(email);


        return respuesta.orElseThrow(()-> new Exepcion("Usuario no existe"));

    }

    public Administrador buscarByNombreAndApedillo(String nombre, String apellido )throws Exepcion{

        Optional<Administrador> respuesta = administradorRepositorio.findByNombreAndApellido(nombre,apellido);

        return respuesta.orElseThrow(()-> new Exepcion("Usuario no existe"));
    }

    public Administrador getOne(String id){
        return administradorRepositorio.getReferenceById(id);
    }

    public List<Administrador> listarAdministradores(){
        return new ArrayList<>(administradorRepositorio.findAll());
    }

    private void validar (String nombre, String apellido, String email, String telefono, String password) throws Exepcion{


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

        if (password.length()<=6){
            throw new Exepcion("La celda contraseña no tiene la longitud correcta");
        }

    }
}
