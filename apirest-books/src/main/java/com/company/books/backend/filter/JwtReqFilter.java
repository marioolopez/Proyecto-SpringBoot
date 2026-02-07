package com.company.books.backend.filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.company.books.backend.service.JwtService;

@Component
public class JwtReqFilter extends OncePerRequestFilter { //esta clase verifica que un usuario es válido y tiene los roles correctos

    @Autowired
    private UserDetailsService servicioDetallesUsuario; //servicio que carga los detalles del usuario

    @Autowired
    private JwtService servicioJwt; //servicio que maneja el JWT (extrae usuario, valida token, etc.)

    @Override
    protected void doFilterInternal(HttpServletRequest solicitud, HttpServletResponse respuesta, FilterChain cadenaFiltros) throws ServletException, IOException {

    	final String cabeceraAutorizacion = solicitud.getHeader("Authorization"); 
        //extrae el header "Authorization" de la petición
        

        String nombreUsuario = null; //username a vacio
        String tokenJwt = null;      //jwt a vacio

        
        if(cabeceraAutorizacion != null && cabeceraAutorizacion.startsWith("Bearer ")) {
            tokenJwt = cabeceraAutorizacion.substring(7); 
            //Quita "Bearer " y queda solo el token
            nombreUsuario = servicioJwt.extractUsername(tokenJwt); 
            //Extrae el usuario del token
        }

        if(nombreUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails detallesUsuario = this.servicioDetallesUsuario.loadUserByUsername(nombreUsuario); 
            //Carga los detalles del usuario desde el servicio

            if (servicioJwt.validateToken(tokenJwt, detallesUsuario)) { 
                //Si el token es válido para ese usuario

                UsernamePasswordAuthenticationToken tokenAutenticacion = new UsernamePasswordAuthenticationToken(
                        detallesUsuario, null, detallesUsuario.getAuthorities());
                tokenAutenticacion.setDetails(new WebAuthenticationDetailsSource().buildDetails(solicitud));
                SecurityContextHolder.getContext().setAuthentication(tokenAutenticacion);
                //Guarda la autenticación en el contexto de Spring Security
            }
        }

        cadenaFiltros.doFilter(solicitud, respuesta); 
        //Continúa con el siguiente filtro de la cadena
    }

}