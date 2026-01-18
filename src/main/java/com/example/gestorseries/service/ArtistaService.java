package com.example.gestorseries.service;

import com.example.gestorseries.model.Artista;

import java.util.List;

public interface ArtistaService {
    Artista crear(Artista artista);
    Artista obtenerPorId(Long id);
    List<Artista> listar();
    void eliminar(Long id);
}
