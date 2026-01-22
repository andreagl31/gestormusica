package com.example.gestormusica.controllers;

//el controlador habla con el servicio y controla las peticiones q le llegan del front

import com.example.gestormusica.dtos.CancionDTO;
import com.example.gestormusica.model.Cancion;
import com.example.gestormusica.service.CancionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// esta es la ruta a la cual haremos peticiones
@RequestMapping ("/api/canciones")
@RequiredArgsConstructor
//esto es para q se haga automñático el constrcutor
public class CancionController {
    private final CancionService cancionService;

    //ahora vamos a controlar las peticiones desde el front
    //te devuelve la cancion creada y recibe en el request body la cancion a crear,
    //llama al mñetodo del constructor para crearla
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CancionDTO> crearCancion(@RequestBody Cancion cancion){
        return ResponseEntity.ok(cancionService.crear(cancion));
    }
    //lista de canciones en todal
    @GetMapping
    public ResponseEntity<List<CancionDTO>> obtenerCanciones(){
        return ResponseEntity.ok(cancionService.listar());

    }
    //obtener por id
    @GetMapping("/{id}")
    //esta variable va en la ruta y no en el body ya que solo es un pequeño id y nos tenemos q meter
    // a el mediante la ruta en vez de ir en el cuerpo
    public  ResponseEntity<CancionDTO> obtenerCancion(@PathVariable Long id){
        return ResponseEntity.ok(cancionService.obtenerPorId(id));

    }

    //eliminar
    // esto es para revisar si ed administrador
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cancionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }



}
