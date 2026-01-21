package com.example.gestorseries.testunitarios;

import com.example.gestorseries.Excepciones.ResourceNotFoundException;
import com.example.gestorseries.model.Cancion;
import com.example.gestorseries.repository.CancionRepository;
import com.example.gestorseries.service.implementaciones.CancionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CacionServiceUnitTest {
    @Mock
    private CancionRepository cancionRepo;

    @InjectMocks
    private CancionServiceImpl cancionService;

    private Cancion cancion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cancion = new Cancion();
        cancion.setId(1L);
        cancion.setTitulo("Mi Cancion");
        cancion.setGenero("Pop");
    }

    @Test
    void testCrearCancion() {
        when(cancionRepo.save(cancion)).thenReturn(cancion);

        var dto = cancionService.crear(cancion);

        assertEquals("Mi Cancion", dto.getTitulo());
        verify(cancionRepo, times(1)).save(cancion);
    }

    @Test
    void testObtenerPorIdExistente() {
        when(cancionRepo.findById(1L)).thenReturn(Optional.of(cancion));

        var dto = cancionService.obtenerPorId(1L);

        assertEquals("Mi Cancion", dto.getTitulo());
    }

    @Test
    void testObtenerPorIdNoExistente() {
        when(cancionRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cancionService.obtenerPorId(1L));
    }

    @Test
    void testListarCanciones() {
        when(cancionRepo.findAll()).thenReturn(List.of(cancion));

        var lista = cancionService.listar();

        assertEquals(1, lista.size());
        assertEquals("Mi Cancion", lista.get(0).getTitulo());
    }

    @Test
    void testEliminarCancion() {
        doNothing().when(cancionRepo).deleteById(1L);

        cancionService.eliminar(1L);

        verify(cancionRepo, times(1)).deleteById(1L);
    }
}
