package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.ClienteRepositorio;
import com.serviciosYa.servicios.interfaces.IClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteServicio implements IClienteServicio {

    private ClienteRepositorio clienteRepositorio;
    @Transactional
    public void crear(String nombre, String apellido,String direccion, String email, String telefono, String password,String password2,Rol rol) throws Exepcion {

        validar(nombre,apellido,email,direccion,telefono,password);
        validarPasswords(password,password2);

        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setPassword(
                new BCryptPasswordEncoder().encode(password)
        );
        cliente.setRol(rol);
        cliente.setActivo(true);
        clienteRepositorio.save(cliente);
    }

    @Transactional
    public void modificarById (String id,String nombre, String apellido,String direccion, String email, String telefono, String password,String password2) throws Exepcion {

        Cliente cliente = buscarByID(id);

        validar(nombre,apellido,email,direccion,telefono,password);
        validarPasswords(password,password2);

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        clienteRepositorio.save(cliente);

    }

    @Transactional
    public void eliminarById (String id) throws Exepcion {

        Cliente usuario = buscarByID(id);
        usuario.setActivo(false);
        clienteRepositorio.save(usuario);

    }

    public Cliente buscarByID(String id)throws Exepcion{

        Optional<Cliente> repuesta = clienteRepositorio.findById(id);

        return repuesta.orElseThrow(()-> new Exepcion("Cliente no existe"));
    }


    public Cliente buscarByEmail(String email) throws Exepcion{

        Optional<Cliente> repuesta = clienteRepositorio.findByEmail(email);
        return repuesta.orElseThrow(()-> new Exepcion("Cliente no existe"));

    }

    public Cliente buscarByNombreAndApedillo(String nombre, String apellido )throws Exepcion{

        Optional<Cliente> repuesta = clienteRepositorio.findByNombreAndApellido(nombre,apellido);

        return repuesta.orElseThrow(()-> new Exepcion("Cliente no existe"));
    }

    public Cliente getOne (String id){
        return clienteRepositorio.getReferenceById(id);
    }

    public List<Cliente> listarClientes (){
        return new ArrayList<>(clienteRepositorio.findAll());
    }

    private void validar (String nombre, String apellido, String email, String telefono,String direccion, String password) throws Exepcion{


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

        if (direccion.isEmpty()){
            throw new Exepcion("La celda direccion esta vacia");
        }

        if (password.length()<=6){
            throw new Exepcion("La celda contraseña no tiene la longitud correcta");
        }
    }

    private void validarPasswords(String password1, String password2) throws Exepcion{
        if (!password1.equals(password2)){
            throw new Exepcion("Las contraseñas no coinciden");
        }
    }


}
