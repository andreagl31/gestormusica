package com.example.gestormusica.controllers;

import com.example.gestormusica.dtos.PerfilDTO;
import com.example.gestormusica.model.Perfil;
import com.example.gestormusica.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
@RequiredArgsConstructor
public class PerfilController {
    private final PerfilService perfilService;

    // Crear perfil
    @PostMapping
    public ResponseEntity<PerfilDTO> crear(@RequestBody Perfil perfil) {
        return ResponseEntity.ok(perfilService.crear(perfil));
    }

    // Listar perfiles con usuario
    @GetMapping
    public ResponseEntity<List<PerfilDTO>> listar() {
        return ResponseEntity.ok(perfilService.listar());
    }

    // Obtener perfil con usuario
    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.obtenerPorId(id));
    }
}
