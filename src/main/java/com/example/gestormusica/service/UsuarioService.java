package com.example.gestormusica.service;

import com.example.gestormusica.dtos.UsuarioDTO;
import com.example.gestormusica.model.Usuario;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO crear (Usuario usuario);
    UsuarioDTO obtenerPorId (Long id);
    List<UsuarioDTO> listar ();
    void eliminar(Long id);

}
