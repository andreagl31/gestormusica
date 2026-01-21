package com.example.gestorseries.testunitarios;
import com.example.gestorseries.dtos.AlbumDTO;
import com.example.gestorseries.model.Album;
import com.example.gestorseries.repository.AlbumRepository;
import com.example.gestorseries.service.implementaciones.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class AlbumServiceUnitTest {
    @Mock
    private AlbumRepository albumRepo;

    @InjectMocks
    private AlbumServiceImpl albumService;

    private Album album;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        album = new Album();
        album.setId(1L);
        album.setTitulo("Mi Album");
    }

    @Test
    void testCrearAlbum() {
        when(albumRepo.save(album)).thenReturn(album);

        AlbumDTO resultado = albumService.crear(album);

        assertEquals("Mi Album", resultado.getTitulo());
        verify(albumRepo, times(1)).save(album);
    }

    @Test
    void testObtenerPorId() {
        when(albumRepo.findById(1L)).thenReturn(Optional.of(album));

        var dto = albumService.obtenerPorId(1L);

        assertNotNull(dto);
        assertEquals("Mi Album", dto.getTitulo());
    }

    @Test
    void testListarAlbumes() {
        when(albumRepo.findAll()).thenReturn(List.of(album));

        List<AlbumDTO> lista = albumService.listar();

        assertEquals(1, lista.size());
    }
}

