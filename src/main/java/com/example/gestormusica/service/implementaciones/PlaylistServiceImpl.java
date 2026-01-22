package com.example.gestormusica.service.implementaciones;

import com.example.gestormusica.dtos.CancionSimpleDTO;
import com.example.gestormusica.dtos.PlaylistDTO;
import com.example.gestormusica.model.Cancion;
import com.example.gestormusica.model.Playlist;
import com.example.gestormusica.repository.CancionRepository;
import com.example.gestormusica.repository.PlaylistRepository;
import com.example.gestormusica.service.PlaylistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImpl implements PlaylistService {


    //inicializamos los dtos
    private CancionSimpleDTO toCancionSimpleDTO(Cancion c) {
        CancionSimpleDTO dto = new CancionSimpleDTO();
        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());
        dto.setGenero(c.getGenero());
        return dto;
    }
    private PlaylistDTO toPlaylistDTO(Playlist p) {
        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setPublica(p.isPublica());

        if (p.getCanciones() != null) {
            dto.setCanciones(
                    p.getCanciones()
                            .stream()
                            .map(this::toCancionSimpleDTO)
                            .collect(Collectors.toSet())
            );
        }

        return dto;
    }


    private final PlaylistRepository playlistRepo;
    private final CancionRepository cancionRepo;

    public PlaylistServiceImpl(PlaylistRepository playlistRepo, CancionRepository cancionRepo) {
        this.playlistRepo = playlistRepo;
        this.cancionRepo = cancionRepo;
    }

    @Override
    public PlaylistDTO crear(Playlist playlist) {
        return toPlaylistDTO(playlistRepo.save(playlist));
    }

    @Override
    public PlaylistDTO obtenerPorId(Long id) {
        Playlist playlist = playlistRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist no encontrada"));

        return toPlaylistDTO(playlist);
    }

    @Override
    public List<PlaylistDTO> listar() {
        return playlistRepo.findAll()
                .stream()
                .map(this::toPlaylistDTO)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        playlistRepo.deleteById(id);
    }

    @Override
    public void añadirCancion(Long playlistId, Long cancionId) {
        Playlist playlist = playlistRepo.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist no encontrada"));
        Cancion cancion = cancionRepo.findById(cancionId)
                .orElseThrow(() -> new RuntimeException("Canción no encontrada"));
        //Lombok crea automáticamente get canciones al ver el atributo canciones en playlist
        playlist.getCanciones().add(cancion);
        playlistRepo.save(playlist);
    }
}
