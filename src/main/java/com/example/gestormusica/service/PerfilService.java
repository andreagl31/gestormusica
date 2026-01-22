package com.example.gestormusica.service;

import com.example.gestormusica.dtos.PerfilDTO;
import com.example.gestormusica.model.Perfil;

import java.util.List;

public interface PerfilService {
    PerfilDTO crear(Perfil perfil);
    PerfilDTO obtenerPorId(Long id);
    List<PerfilDTO> listar();
    void eliminar(Long id);
}
