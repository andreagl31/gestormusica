package com.example.gestormusica.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

//es un filtro que intengra sprin security en los tokens el método que tiene
//se ejecuta en cada petición http para valdiar el token y guardarlo en el contexto de seguridad que tienes
//por eso tienes q heredar de la clase OncePerRequestFilter
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    //USERDETAILSERVICE es una interfaz de spring security la cual
    //spring la usa para saber que cyuando hace intenta hyacer kliogin
    //Sacar su usuario y contraseña
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws java.io.IOException, jakarta.servlet.ServletException {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtUtil.validarToken(token)) {
                String username = jwtUtil.obtenerUsername(token); //aqui obtenemos el nombre de usuario por el token
                var userDetails = userDetailsService.loadUserByUsername(username); //nos intentamos logear con el usuario
                //obtenemos el role del token y lo convertimos en autoridad para spting
                var role = jwtUtil.obtenerRole(token);
                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

                var auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response); //aqui cogemos la repsuesta ty la request
    }
}
