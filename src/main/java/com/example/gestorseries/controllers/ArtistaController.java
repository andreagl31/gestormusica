package com.example.gestorseries.controllers;

import com.example.gestorseries.model.Artista;
import com.example.gestorseries.service.ArtistaService;
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
    public ResponseEntity<Artista> crear(@RequestBody Artista artista) {
        return ResponseEntity.ok(artistaService.crear(artista));
    }

    // Listar artistas con canciones
    @GetMapping
    public ResponseEntity<List<Artista>> listar() {
        return ResponseEntity.ok(artistaService.listar());
    }

    // Obtener artista con canciones
    @GetMapping("/{id}")
    public ResponseEntity<Artista> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(artistaService.obtenerPorId(id));
    }
}
