package com.example.gestormusica.Integracion;

import com.example.gestormusica.model.Role;
import com.example.gestormusica.model.Usuario;
import com.example.gestormusica.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//en los test de integración no estamos probando métodos aislados
//como podría ser en pruebas unitarias, si no que estamos probando el flujo completo
//petición-> controlador-> servicio-> repositorio->base datos
// esto usa springboottest y mockito
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UsuarioIntegrationTest {
    //se importa automáticamente con autowired,
    //autowired es un servicio q te permite importar un bean de otra clase
    //un bean es un objeto qu puedde representar cualquier cosa de tora clase
    //como un servicio,clase etc
    private String token; //jwt del usuario user
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {
        usuarioRepo.deleteAll();

        Usuario user = new Usuario();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRole(Role.USUARIO);
        user.setActivo(true);
        user.setFechaRegistro(LocalDate.now());
        usuarioRepo.save(user);

        // Login y obtener token JWT
        String loginJson = """
        {
            "username": "user",
            "password": "1234"
        }
    """;

        token = mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    //Crear usuario
    @Test
    void testRegisterUsuario() throws Exception {
        String usuarioJson = """
            {
                "username": "maria",
                "password": "123456",
                "email": "maria@mail.com"
            }
            """;
        //probamos el usuario haciendo una petición a mi url
        //le mandamos el usuario que hemos creado arriba
        mockMvc.perform(post("/api/usuarios/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario registrado correctamente"));

        // Validamos que se guardó en BD
        assertTrue(usuarioRepo.findByUsername("maria").isPresent());
    }
    //Listar usuarios
    @Test
    void testListarUsuarios() throws Exception {
        mockMvc.perform(get("/api/usuarios")
                        .header("Authorization", "Bearer " + token)) // añadir token
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    // $ representa la raíz del json que devuelve la respuesta http
    // hacemos una petición a mi url de nuevo y ponemos q esperamos q todo vaya ok
    //obtener usuario por id
    @Test
    void testObtenerUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("juan");
        usuario.setPassword(passwordEncoder.encode("1234"));
        usuario.setEmail("juan@mail.com");
        usuario.setActivo(true);
        usuario.setRole(Role.USUARIO);
        usuario.setFechaRegistro(LocalDate.now());
        usuarioRepo.save(usuario);
         //estamos haciendo una petición al usuario por su id y esperando q sea ojk
        mockMvc.perform(get("/api/usuarios/" + usuario.getId())
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                // del array que te devuelva de objetos json o el objeto json en este caso
                // -> $ dame el parámetro username
                .andExpect(jsonPath("$.username").value("juan"));
    }
    // hacer login
    @Test
    void testLogin() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("anita");
        usuario.setPassword(passwordEncoder.encode("1234"));
        usuario.setEmail("anita@mail.com");
        usuario.setActivo(true);
        usuario.setRole(Role.USUARIO);
        usuario.setFechaRegistro(LocalDate.now());
        usuarioRepo.save(usuario);

        String loginJson = """
            {
                "username": "anita",
                "password": "1234"
            }
            """;
        //intento hacer una request al login
        mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.notNullValue()));
    }

}
