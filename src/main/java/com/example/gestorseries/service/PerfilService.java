package com.example.gestorseries.service;

import com.example.gestorseries.model.Perfil;

import java.util.List;

public interface PerfilService {
    Perfil crear(Perfil perfil);
    Perfil obtenerPorId(Long id);
    List<Perfil> listar();
    void eliminar(Long id);
}
