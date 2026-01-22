package com.example.gestormusica.service;

import com.example.gestormusica.dtos.ArtistaDTO;
import com.example.gestormusica.model.Artista;

import java.util.List;

public interface ArtistaService {
    ArtistaDTO crear(Artista artista);
    ArtistaDTO obtenerPorId(Long id);
    List<ArtistaDTO> listar();
    void eliminar(Long id);

}
