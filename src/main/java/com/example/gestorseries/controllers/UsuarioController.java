package com.example.gestorseries.controllers;

import com.example.gestorseries.dtos.UsuarioDTO;
import com.example.gestorseries.model.Usuario;
import com.example.gestorseries.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor

public class UsuarioController {
    private final UsuarioService usuarioService;

    // Crear usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crear(usuario));
    }
    // Listar usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }
    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    // Listar playlists del usuario
    @GetMapping("/{id}/playlists")
    public ResponseEntity<Set<?>> obtenerPlaylists(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario.getPlaylists());
    }
    // Listar canciones favoritas del usuario
    @GetMapping("/{id}/favoritas")
    public ResponseEntity<Set<?>> obtenerFavoritas(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario.getFavoritas());
    }
    //Listar usuario con perfil
    @GetMapping("/con-perfil")
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosConPerfil() {
        return ResponseEntity.ok(usuarioService.listar());
    }


}
