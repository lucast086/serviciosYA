package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Imagen;
import com.serviciosYa.entidades.Oficio;
import com.serviciosYa.entidades.Proveedor;
import com.serviciosYa.enums.Rol;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.repositorios.ProveedorRepositorio;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProveedorServicio implements IProveedorServicio {

    ProveedorRepositorio proveedorRepositorio;
    ImagenServicio imagenServicio;

    @Override
    public void crear(String nombre, String apellido, String email, String telefono, String password, String password2, MultipartFile imagen, List<Oficio> oficios, Rol rol) throws Exepcion {

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

        Imagen imagen1 = imagenServicio.guardar(imagen);

        proveedor.setImagen(imagen1);
        proveedor.setRol(rol);
        proveedor.setActivo(true);
        proveedorRepositorio.save(proveedor);
    }

    @Override
    public void modificarByID(String id, String nombre, String apellido, String email, String telefono, String password, String password2, MultipartFile imagen, List<Oficio> oficios) throws Exepcion {
        validar(nombre,apellido,email,oficios,telefono,password);
        validarPasswords(password,password2);

        Proveedor proveedor = buscarByID(id);

        proveedor.setNombre(nombre);
        proveedor.setApellido(apellido);
        proveedor.setEmail(email);
        proveedor.setTelefono(telefono);
        proveedor.setPassword(
                new BCryptPasswordEncoder().encode(password)
        );
        proveedor.setOficios(oficios);

        Imagen imagen1;
        if (proveedor.getImagen() != null){
            String imgID = proveedor.getImagen().getId();
            try {
                imagen1 = imagenServicio.modificar(imagen, imgID);
            } catch (IOException ioException) {
                throw new Exepcion("error en la carga de imagen");
            }
        } else {
            imagen1 = imagenServicio.guardar(imagen);
        }

        proveedor.setImagen(imagen1);
        proveedor.setActivo(true);
        proveedorRepositorio.save(proveedor);
    }

    public Proveedor buscarByID(String id) throws Exepcion {
        Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);
        return respuesta.orElseThrow(() -> new Exepcion("el proveedor no existe"));
    }

    public Proveedor buscarByEmail(String email) throws Exepcion{

        Optional<Proveedor> repuesta = proveedorRepositorio.findByEmail(email);
        return repuesta.orElseThrow(()-> new Exepcion("Proveedor no existe"));

    }

    @Override
    public void eliminarById(String id) throws Exepcion {

        Proveedor proveedor = buscarByID(id);
        proveedor.setActivo(false);
        proveedorRepositorio.save(proveedor);

    }

    @Override
    public Proveedor buscarByNombreAndApellido(String nombre, String apellido) throws Exepcion {

        Optional<Proveedor> respuesta = proveedorRepositorio.findByNombreAndApellido(nombre,apellido);

        return respuesta.orElseThrow(()->new Exepcion("El proveedor no existe"));
    }

    @Override
    public Proveedor getOne(String id) {
        return proveedorRepositorio.getReferenceById(id);
    }

    @Override
    public List<Proveedor> listarProveedores() {
        return new ArrayList<>(proveedorRepositorio.findAll());
    }


    private void validar (String nombre, String apellido, String email,List<Oficio> oficios, String telefono, String password) throws Exepcion{

        if(oficios == null){
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
