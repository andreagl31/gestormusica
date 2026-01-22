package com.example.gestormusica.service;

import com.example.gestormusica.dtos.PlaylistDTO;
import com.example.gestormusica.model.Playlist;

import java.util.List;

public interface PlaylistService {
    PlaylistDTO crear(Playlist playlist);
    PlaylistDTO obtenerPorId(Long id);
    List<PlaylistDTO> listar();
    void eliminar(Long id);
    void añadirCancion(Long playlistId, Long cancionId);
}
