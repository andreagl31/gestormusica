package com.example.gestormusica.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CancionDTO {
    private Long id;
    private String titulo;
    private int duracion;
    private String genero;
    private long reproducciones;
    private LocalDate fechaPublicacion;

    // solo referencias, no objetos completos
    private Long albumId;
}
