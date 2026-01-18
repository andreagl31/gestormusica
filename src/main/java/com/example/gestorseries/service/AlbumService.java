package com.example.gestorseries.service;

import com.example.gestorseries.model.Album;

import java.util.List;

public interface AlbumService {
    Album crear(Album album);
    Album obtenerPorId(Long id);
    List<Album> listar();
    void eliminar(Long id);
}
