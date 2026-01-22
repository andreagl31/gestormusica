package com.example.gestormusica.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class ArtistaDTO {
    private Long id;
    private String nombre;
    //para q sea un pequeño dto
    private Set<CancionSimpleDTO> canciones;
}
