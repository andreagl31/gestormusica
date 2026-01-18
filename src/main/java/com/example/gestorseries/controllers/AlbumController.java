package com.example.gestorseries.controllers;

import com.example.gestorseries.model.Album;
import com.example.gestorseries.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albumes")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    // Crear álbum
    @PostMapping
    public ResponseEntity<Album> crear(@RequestBody Album album) {
        return ResponseEntity.ok(albumService.crear(album));
    }

    // Listar álbumes con canciones
    @GetMapping
    public ResponseEntity<List<Album>> listar() {
        return ResponseEntity.ok(albumService.listar());
    }

    // Obtener álbum con canciones
    @GetMapping("/{id}")
    public ResponseEntity<Album> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.obtenerPorId(id));
    }

}
