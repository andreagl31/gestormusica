package com.example.gestormusica.testunitarios;
import com.example.gestormusica.dtos.UsuarioDTO;
import com.example.gestormusica.model.Usuario;
import com.example.gestormusica.repository.UsuarioRepository;
import com.example.gestormusica.service.implementaciones.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceUnitTest {
    @Mock
    private UsuarioRepository usuarioRepo;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("testUser");
    }

    @Test
    void testCrearUsuario() {
        when(usuarioRepo.save(usuario)).thenReturn(usuario);

        UsuarioDTO resultado = usuarioService.crear(usuario);

        assertEquals("testUser", resultado.getUsername());
        verify(usuarioRepo, times(1)).save(usuario);
    }

    @Test
    void testObtenerUsuarioPorId() {
        when(usuarioRepo.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioDTO resultado = usuarioService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testListarUsuarios() {
        when(usuarioRepo.findAll()).thenReturn(List.of(usuario));

        List<UsuarioDTO> lista = usuarioService.listar();

        assertEquals(1, lista.size());
        verify(usuarioRepo, times(1)).findAll();
    }

    @Test
    void testEliminarUsuario() {
        doNothing().when(usuarioRepo).deleteById(1L);

        usuarioService.eliminar(1L);

        verify(usuarioRepo, times(1)).deleteById(1L);
    }
}
