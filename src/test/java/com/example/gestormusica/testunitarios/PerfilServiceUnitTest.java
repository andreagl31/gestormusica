package com.example.gestormusica.testunitarios;
import com.example.gestormusica.Excepciones.ResourceNotFoundException;
import com.example.gestormusica.model.Perfil;
import com.example.gestormusica.repository.PerfilRepository;
import com.example.gestormusica.service.implementaciones.PerfilServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class PerfilServiceUnitTest {
    @Mock
    private PerfilRepository perfilRepo;

    @InjectMocks
    private PerfilServiceImpl perfilService;

    private Perfil perfil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        perfil = new Perfil();
        perfil.setId(1L);
        perfil.setApellido("Perez");
        perfil.setFechaNacimiento(LocalDate.of(1990,1,1));
    }

    @Test
    void testCrearPerfil() {
        when(perfilRepo.save(perfil)).thenReturn(perfil);

        var dto = perfilService.crear(perfil);

        assertEquals("Perez", dto.getApellido());
        verify(perfilRepo, times(1)).save(perfil);
    }

    @Test
    void testObtenerPorIdExistente() {
        when(perfilRepo.findById(1L)).thenReturn(Optional.of(perfil));

        var dto = perfilService.obtenerPorId(1L);

        assertEquals("Perez", dto.getApellido());
    }

    @Test
    void testObtenerPorIdNoExistente() {
        when(perfilRepo.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            perfilService.obtenerPorId(1L);
        });

        assertEquals("Perfil no encontrado con id 1", exception.getMessage()); // el servicio retorna null si no encuentra perfil
    }

    @Test
    void testListarPerfiles() {
        when(perfilRepo.findAll()).thenReturn(List.of(perfil));

        var lista = perfilService.listar();

        assertEquals(1, lista.size());
        assertEquals("Perez", lista.get(0).getApellido());
    }

    @Test
    void testEliminarPerfil() {
        doNothing().when(perfilRepo).deleteById(1L);

        perfilService.eliminar(1L);

        verify(perfilRepo, times(1)).deleteById(1L);
    }
}
