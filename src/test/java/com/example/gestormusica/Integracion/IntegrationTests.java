package com.example.gestormusica.Integracion;

import com.example.gestormusica.model.*;
import com.example.gestormusica.repository.*;
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
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class IntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // convierte objetos a JSON

    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PerfilRepository perfilRepo;
    @Autowired
    private AlbumRepository albumRepo;
    @Autowired
    private CancionRepository cancionRepo;
    @Autowired
    private PlaylistRepository playlistRepo;
    @Autowired
    private ArtistaRepository artistaRepo;
    @Autowired
    private JoinTableRepository joinRepo;

    private Usuario testUsuario;
    private String tokenAdmin;
    @BeforeEach
    public void setup() throws Exception {
        // Limpiar la base de datos antes de cada test, esto es importante
        //para evitarnos errores
        joinRepo.clearPlaylistCancion();
        joinRepo.clearArtistaCancion();
        joinRepo.clearUsuarioCancion();
        joinRepo.clearUsuarioPlaylist();

        // Primero relaciones
        artistaRepo.deleteAll();
        playlistRepo.deleteAll();
        usuarioRepo.deleteAll();

        // Luego hijas
        cancionRepo.deleteAll();
        albumRepo.deleteAll();
        perfilRepo.deleteAll();

        // Crear un usuario de prueba, tiene q ser admin
        testUsuario = new Usuario();
        testUsuario.setUsername("andrea");
        testUsuario.setPassword(passwordEncoder.encode("1234"));
        testUsuario.setEmail("andrea@email.com");
        testUsuario.setActivo(true);
        testUsuario.setRole(Role.USUARIO);
        testUsuario.setFechaRegistro(LocalDate.now());
        usuarioRepo.save(testUsuario);
        // login admin para obtener JWT
        String loginJson = """
        {
            "username": "andrea",
            "password": "1234"
        }
    """;
        tokenAdmin = mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // Crear perfil
        Perfil perfil = new Perfil();
        perfil.setNombre("Andrea");
        perfil.setApellido("González");
        perfil.setFechaNacimiento(LocalDate.of(2006,5,31));
        perfil.setPais("España");
        perfil.setUsuario(testUsuario);
        perfilRepo.save(perfil);

        testUsuario.setPerfil(perfil);
        usuarioRepo.save(testUsuario);
    }
    //test usuario
    @Test
    public void testCrearUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("ana");
        usuario.setPassword("abcd");
        usuario.setEmail("ana@email.com");
        usuario.setActivo(true);
        usuario.setRole(Role.USUARIO);
        usuario.setFechaRegistro(LocalDate.now());

        mockMvc.perform(post("/api/usuarios")
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("ana"))
                .andExpect(jsonPath("$.email").value("ana@email.com"));
    }
    @Test
    public void testListarUsuarios() throws Exception {
        mockMvc.perform(get("/api/usuarios")
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("andrea"));
    }

    //test de albumes y canciones
    @Test
    public void testCrearAlbumConCancion() throws Exception {
        Album album = new Album();
        album.setTitulo("Mi Album");
        album.setGenero("Pop");
        album.setFechaLanzamiento(LocalDate.now());

        Cancion cancion = new Cancion();
        cancion.setTitulo("Mi Cancion");
        cancion.setGenero("Pop");
        cancion.setDuracion(200);
        cancion.setFechaPublicacion(LocalDate.now());
        cancion.setAlbum(album);

        album.setCanciones(Collections.singletonList(cancion));

        mockMvc.perform(post("/api/albumes")
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Mi Album"))
                .andExpect(jsonPath("$.canciones[0].titulo").value("Mi Cancion"));
    }
    //test de playlists
    @Test
    public void testCrearPlaylist() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setNombre("Mi Playlist");
        playlist.setPublica(true);

        mockMvc.perform(post("/api/playlists")
                        .header("Authorization", "Bearer " + tokenAdmin)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playlist)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Mi Playlist"))
                .andExpect(jsonPath("$.publica").value(true));
    }
    //test artista
    @Test
    public void testCrearArtista() throws Exception {
        Artista artista = new Artista();
        artista.setNombre("Shakira");
        artista.setPais("Colombia");
        artista.setFecha(LocalDate.of(1980,2,2));
        artista.setBiografia("Cantante famosa");

        mockMvc.perform(post("/api/artistas")
                        .header("Authorization","Bearer "+tokenAdmin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(artista)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Shakira"))
                .andExpect(jsonPath("$.canciones").isEmpty());
    }
}
