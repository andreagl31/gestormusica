package com.example.gestorseries.testunitarios;

import com.example.gestorseries.model.Cancion;
import com.example.gestorseries.model.Playlist;
import com.example.gestorseries.repository.CancionRepository;
import com.example.gestorseries.repository.PlaylistRepository;
import com.example.gestorseries.service.implementaciones.PlaylistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class PlaylistServiceUnitTest {
    @Mock
    private PlaylistRepository playlistRepo;

    @Mock
    private CancionRepository cancionRepo;

    @InjectMocks
    private PlaylistServiceImpl playlistService;

    private Playlist playlist;
    private Cancion cancion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        playlist = new Playlist();
        playlist.setId(1L);
        playlist.setNombre("Mi Playlist");

        cancion = new Cancion();
        cancion.setId(1L);
        cancion.setTitulo("Cancion 1");
    }

    @Test
    void testCrearPlaylist() {
        when(playlistRepo.save(playlist)).thenReturn(playlist);

        var dto = playlistService.crear(playlist);

        assertEquals("Mi Playlist", dto.getNombre());
    }

    @Test
    void testAñadirCancion() {
        when(playlistRepo.findById(1L)).thenReturn(Optional.of(playlist));
        when(cancionRepo.findById(1L)).thenReturn(Optional.of(cancion));
        when(playlistRepo.save(playlist)).thenReturn(playlist);

        playlistService.añadirCancion(1L, 1L);

        assertTrue(playlist.getCanciones().contains(cancion));
    }
}
