package com.example.gestorseries.controllers;

import com.example.gestorseries.Security.CustomUserDetailsService;
import com.example.gestorseries.Security.JwtUtil;
import com.example.gestorseries.dtos.RegisterRequestDTO;
import com.example.gestorseries.model.Role;
import com.example.gestorseries.model.Usuario;
import com.example.gestorseries.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

//esta clase sirve para cuando tu haces login te crea el usuario y contraseña
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepo;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest login) {
        var authToken = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                login.getUsername(), login.getPassword()
        );
        //esto válida usuario y contraseña
        authManager.authenticate(authToken);
        //cargamos usuario de spring security
        var userDetails = userDetailsService.loadUserByUsername(login.getUsername()); //usamos la
        //clase realizada antes para logearnos con el nombre de usuario

        // Obtenemos el rol real del usuario desde BD
        Usuario usuario = usuarioRepo.findByUsername(login.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Generamos JWT con ROLE dentro

        String token = jwtUtil.generarToken(userDetails.getUsername(),usuario.getRole()); //generamos el token
        //q  va a fitrmar el login
        //primero se ejecuta el controlador gracias a la rutaa de login, este llamara al servicio
        //de logear y el servicio de logear se encargara de comunicarse con el repositorio

        return ResponseEntity.ok(token);
    }
    //este endpoint sirve para registrarnos
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO request) {

        if (usuarioRepo.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuario ya existe");
        } //te comprueba si el usuario sirve

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername()); //coge el nombre y la contraseña y los codifica
        usuario.setPassword(passwordEncoder.encode(request.getPassword())); //esto es clave por seguridad
        usuario.setEmail(request.getEmail());
        usuario.setActivo(true); //activo pk se acaba de conectar
        usuario.setRole(Role.USUARIO);
        usuario.setFechaRegistro(LocalDate.now());

        usuarioRepo.save(usuario); //guardamos el usuario en la base de datos

        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}

@Data
class LoginRequest {
    @NotBlank(message = "El username es obligatorio")
    private String username;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
