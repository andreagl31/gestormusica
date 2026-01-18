package com.example.gestorseries.controllers;

import com.example.gestorseries.model.Perfil;
import com.example.gestorseries.service.PerfilService;
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
    public ResponseEntity<Perfil> crear(@RequestBody Perfil perfil) {
        return ResponseEntity.ok(perfilService.crear(perfil));
    }

    // Listar perfiles con usuario
    @GetMapping
    public ResponseEntity<List<Perfil>> listar() {
        return ResponseEntity.ok(perfilService.listar());
    }

    // Obtener perfil con usuario
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.obtenerPorId(id));
    }
}
