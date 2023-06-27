package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Cliente;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.ProveedorRepositorio;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProveedorServicio implements IProveedorServicio {

    ProveedorRepositorio proveedorRepositorio;
    @Override
    public void crear(String nombre, String apellido, String email, String telefono, String password, String password2, String imagen, List<Oficio> oficios, Rol rol) throws Exepcion {

        validar(nombre,apellido,email,oficios,telefono,password);
        validarPasswords(password,password2);

        Proveedor proveedor = new Proveedor();

        proveedor.setNombre(nombre);
        proveedor.setApellido(apellido);
        proveedor.setEmail(email);
        proveedor.setTelefono(telefono);
        proveedor.setPassword(
                new BCryptPasswordEncoder().encode(password)
        );
        proveedor.setOficios(oficios);
        proveedor.setImagen(imagen);
        proveedor.setRol(rol);
        proveedor.setActivo(true);
        proveedorRepositorio.save(proveedor);
    }

    public Proveedor buscarByEmail(String email) throws Exepcion{

        Optional<Proveedor> repuesta = proveedorRepositorio.findByEmail(email);
        return repuesta.orElseThrow(()-> new Exepcion("Proveedor no existe"));

    }



    private void validar (String nombre, String apellido, String email,List<Oficio> oficios, String telefono, String password) throws Exepcion{

        if(oficios.size()<1){
            throw new Exepcion("No tiene oficios");
        }

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

    private void validarPasswords(String password1, String password2) throws Exepcion{
        if (!password1.equals(password2)){
            throw new Exepcion("Las contraseñas no coinciden");
        }
    }

}
