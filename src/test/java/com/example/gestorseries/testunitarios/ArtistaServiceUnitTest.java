package com.example.gestorseries.testunitarios;
import com.example.gestorseries.Excepciones.ResourceNotFoundException;
import com.example.gestorseries.model.Artista;
import com.example.gestorseries.model.Cancion;
import com.example.gestorseries.repository.ArtistaRepository;
import com.example.gestorseries.service.implementaciones.ArtistaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ArtistaServiceUnitTest {
    @Mock
    private ArtistaRepository artistaRepo;

    @InjectMocks
    private ArtistaServiceImpl artistaService;

    private Artista artista;
    private Cancion cancion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cancion = new Cancion();
        cancion.setId(1L);
        cancion.setTitulo("Cancion 1");

        artista = new Artista();
        artista.setId(1L);
        artista.setNombre("Artista 1");
        artista.setCanciones(Set.of(cancion));
    }

    @Test
    void testCrearArtista() {
        when(artistaRepo.save(artista)).thenReturn(artista);

        var dto = artistaService.crear(artista);

        assertEquals("Artista 1", dto.getNombre());
        assertEquals(1, dto.getCanciones().size());
        verify(artistaRepo, times(1)).save(artista);
    }

    @Test
    void testObtenerPorIdExistente() {
        when(artistaRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> artistaService.obtenerPorId(1L));
    }

    @Test
    void testObtenerPorIdNoExistente() {
        when(artistaRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> artistaService.obtenerPorId(1L));
    }

    @Test
    void testListarArtistas() {
        when(artistaRepo.findAll()).thenReturn(List.of(artista));

        var lista = artistaService.listar();

        assertEquals(1, lista.size());
        assertEquals("Artista 1", lista.get(0).getNombre());
    }

    @Test
    void testEliminarArtista() {
        doNothing().when(artistaRepo).deleteById(1L);

        artistaService.eliminar(1L);

        verify(artistaRepo, times(1)).deleteById(1L);
    }
}
