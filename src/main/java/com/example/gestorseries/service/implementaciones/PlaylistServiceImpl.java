package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.model.Cancion;
import com.example.gestorseries.model.Playlist;
import com.example.gestorseries.repository.CancionRepository;
import com.example.gestorseries.repository.PlaylistRepository;
import com.example.gestorseries.service.PlaylistService;

import java.util.List;

public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepo;
    private final CancionRepository cancionRepo;

    public PlaylistServiceImpl(PlaylistRepository playlistRepo, CancionRepository cancionRepo) {
        this.playlistRepo = playlistRepo;
        this.cancionRepo = cancionRepo;
    }

    @Override
    public Playlist crear(Playlist playlist) {
        return playlistRepo.save(playlist);
    }

    @Override
    public Playlist obtenerPorId(Long id) {
        return playlistRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist no encontrada"));
    }

    @Override
    public List<Playlist> listar() {
        return playlistRepo.findAll();
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
