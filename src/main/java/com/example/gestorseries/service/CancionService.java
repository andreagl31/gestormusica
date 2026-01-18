package com.example.gestorseries.service;

import com.example.gestorseries.model.Cancion;

import java.util.List;

public interface CancionService {
    void eliminar(Long id);
    Cancion obtenerPorId(Long id);
    Cancion crear (Cancion cancion);
    List<Cancion> listar ();
}
//La app se divide en controladores,modelos,repositorios y servicios
//el cliente se comunica con el controlador
//el controlador se comunica con el servicio
// el servicio se comunica con el repositorio
//el repositorio se comunica con la base de datos
