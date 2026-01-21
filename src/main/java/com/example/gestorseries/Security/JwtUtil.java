package com.example.gestorseries.Security;

import com.example.gestorseries.model.Role;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
//Spring si quieres implementar login con jwt necesitas 3 piezas importantes
//Primero necesitas la clase que hará el token que es jwutil
//segundo necesitas la clase del filtro que verifica la firma
//tercero tenemos que establecer unas cfiguraciones de ruta en security condig
@Component
public class JwtUtil {
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // hacemos una secretkey automática para
    //que no nos de fallo porque esk si no te da fallo de muy corta o muy larga ayudaaa
    private final long EXPIRATION = 1000 * 60 * 60; // 1 hora
    public String generarToken(String username,Role role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role.name())//PARA OBTENER USUARIO
                .setIssuedAt(new Date()) //PARA OBTENER LA FECHA DE CREACIÓN
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) //PARA OBTENER LA FECHA DE EXPIRACIÓN
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    // LEER ROLE
    public String obtenerRole(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
    public String obtenerUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

}
