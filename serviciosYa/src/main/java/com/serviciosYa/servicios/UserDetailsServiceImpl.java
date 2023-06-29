package com.serviciosYa.servicios;

import com.serviciosYa.entidades.Usuario;
import com.serviciosYa.exepcion.Exepcion;
import com.serviciosYa.servicios.interfaces.IAdministradorServicio;
import com.serviciosYa.servicios.interfaces.IClienteServicio;
import com.serviciosYa.servicios.interfaces.IProveedorServicio;
import com.serviciosYa.servicios.interfaces.IUsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    IClienteServicio clienteServicio;
    IProveedorServicio proveedorServicio;
    IAdministradorServicio administradorServicio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario;
        try {
            usuario = clienteServicio.buscarByEmail(email);
        } catch (Exepcion e) {
            try {
                usuario = proveedorServicio.buscarByEmail(email);
            } catch (Exepcion ex) {
                try {
                    usuario = administradorServicio.buscarByEmail(email);
                } catch (Exepcion exce) {
                    throw new UsernameNotFoundException("Usuario no registrado");
                }
            }
        }

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority permiso = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(permiso);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuarioSesion", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
    }
}
