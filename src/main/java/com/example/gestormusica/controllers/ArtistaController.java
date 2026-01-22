package com.example.gestormusica.controllers;

import com.example.gestormusica.dtos.ArtistaDTO;
import com.example.gestormusica.model.Artista;
import com.example.gestormusica.service.ArtistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistas")
@RequiredArgsConstructor
public class ArtistaController {
    private final ArtistaService artistaService;

    // Crear artista
    @PostMapping
    public ResponseEntity<ArtistaDTO> crear(@RequestBody Artista artista) {
        return ResponseEntity.ok(artistaService.crear(artista));
    }

    // Listar artistas con canciones
    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> listar() {
        return ResponseEntity.ok(artistaService.listar());
    }

    // Obtener artista con canciones
    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(artistaService.obtenerPorId(id));
    }
}
