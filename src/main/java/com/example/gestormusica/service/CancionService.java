package com.example.gestormusica.service;

import com.example.gestormusica.dtos.CancionDTO;
import com.example.gestormusica.model.Cancion;

import java.util.List;

public interface CancionService {
    void eliminar(Long id);
    CancionDTO obtenerPorId(Long id);
    CancionDTO crear (Cancion cancion);
    List<CancionDTO> listar ();
}
//La app se divide en controladores,modelos,repositorios y servicios
//el cliente se comunica con el controlador
//el controlador se comunica con el servicio
// el servicio se comunica con los dtos
//el repositorio devuelve dtos
//el repositorio se comunica con la base de datos
