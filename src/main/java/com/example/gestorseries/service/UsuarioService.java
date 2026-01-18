package com.example.gestorseries.service;

import com.example.gestorseries.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario crear (Usuario usuario);
    Usuario obtenerPorId (Long id);
    List<Usuario> listar ();
    void eliminar(Long id);
}
