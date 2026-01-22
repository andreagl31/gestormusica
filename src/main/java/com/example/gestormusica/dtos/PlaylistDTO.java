package com.example.gestormusica.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PlaylistDTO {
    private Long id;
    private String nombre;
    private  boolean publica;
    private LocalDate fechaCreacion;
    private Set<CancionSimpleDTO> canciones;
}
