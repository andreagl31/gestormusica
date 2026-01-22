package com.example.gestormusica.controllers;

import com.example.gestormusica.dtos.AlbumDTO;
import com.example.gestormusica.model.Album;
import com.example.gestormusica.service.AlbumService;
import com.example.gestormusica.service.implementaciones.AlbumServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albumes")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    // Crear álbum
    @PostMapping
    public ResponseEntity<AlbumDTO> crear(@RequestBody Album album) {
        return ResponseEntity.ok(albumService.crear(album));
    }

    // Listar álbumes con canciones
    @GetMapping
    public ResponseEntity<List<AlbumDTO>> listar() {
        return ResponseEntity.ok(albumService.listar());
    }

    // Obtener álbum con canciones
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.obtenerPorId(id));
    }
    @GetMapping("/con-canciones")
    public ResponseEntity<List<AlbumDTO>> listarAlbumesConCanciones() {
        return ResponseEntity.ok(albumService.listar());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        albumService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
