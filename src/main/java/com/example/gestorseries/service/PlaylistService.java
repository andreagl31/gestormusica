package com.example.gestorseries.service;

import com.example.gestorseries.model.Playlist;

import java.util.List;

public interface PlaylistService {
    Playlist crear(Playlist playlist);
    Playlist obtenerPorId(Long id);
    List<Playlist> listar();
    void eliminar(Long id);
    void añadirCancion(Long playlistId, Long cancionId);
}
