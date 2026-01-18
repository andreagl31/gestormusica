package com.example.gestorseries.controllers;

import com.example.gestorseries.model.Playlist;
import com.example.gestorseries.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/playlists")
@RequiredArgsConstructor
public class PlaylistControllers {
    private final PlaylistService playlistService;
    //Crear playlist
    @PostMapping
    public ResponseEntity<Playlist> crearPlaylist(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.crear(playlist));
    }
    // Listar todas
    @GetMapping
    public ResponseEntity<List<Playlist>> listar() {
        return ResponseEntity.ok(playlistService.listar());
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.obtenerPorId(id));
    }

    // Eliminar playlist
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        playlistService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    //añadir canciones a la playlist
    @PostMapping ("/{playlistId}/canciones/{cancionId}")
    public ResponseEntity <Void> añadirCancion(@PathVariable Long playlistId, @PathVariable Long cancionId) {
        playlistService.añadirCancion(playlistId, cancionId);
        return ResponseEntity.ok().build();
    }
}
