package com.example.gestormusica.Integracion;

import com.example.gestormusica.model.Role;
import com.example.gestormusica.model.Usuario;
import com.example.gestormusica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Test de integración completo para Usuarios con JWT y roles
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class SecurityIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    private String tokenUsuario;
    private String tokenAdmin;

    @BeforeEach
    //este método se hace antes de ejecutar cada test
    void setup() throws Exception {
        usuarioRepo.deleteAll(); // Limpiar BD antes de cada test

        // Crear usuario normal
        Usuario user = new Usuario();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRole(Role.USUARIO);
        user.setActivo(true);
        user.setFechaRegistro(LocalDate.now());
        usuarioRepo.save(user);

        // Crear usuario admin
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);
        admin.setActivo(true);
        admin.setFechaRegistro(LocalDate.now());
        usuarioRepo.save(admin);

        // Login usuarios y obtener token JWT
        tokenUsuario = loginYObtenerToken("user", "1234");
        tokenAdmin = loginYObtenerToken("admin", "admin123");
    }

    // Helper para loguear y devolver JWT
    private String loginYObtenerToken(String username, String password) throws Exception {
        String loginJson = objectMapper.writeValueAsString(Map.of(
                "username", username,
                "password", password
        ));

        return mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    // Test listar usuarios (protegido)
    @Test
    void testListarUsuariosConToken() throws Exception {
        mockMvc.perform(get("/api/usuarios")
                        .header("Authorization", "Bearer " + tokenUsuario))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("user"));
    }

    // Test endpoint admin
    @Test
    void testBorrarUsuarioSoloAdmin() throws Exception {
        Usuario u = usuarioRepo.findByUsername("user").orElseThrow();

        // Usuario normal no puede borrar
        mockMvc.perform(delete("/api/usuarios/" + u.getId())
                        .header("Authorization", "Bearer " + tokenUsuario))
                .andExpect(status().isForbidden());

        // Admin puede borrar
        mockMvc.perform(delete("/api/usuarios/" + u.getId())
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isNoContent());
    }

    // Test registro
    @Test
    void testRegistroUsuario() throws Exception {
        String registroJson = objectMapper.writeValueAsString(Map.of(
                "username", "nuevo",
                "password", "pass123",
                "email", "nuevo@test.com"
        ));

        mockMvc.perform(post("/api/usuarios/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registroJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario registrado correctamente"));
    }
}
