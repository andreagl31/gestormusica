package com.example.gestormusica.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AlbumDTO {
    private Long id;
    private String titulo;
    private String genero;
    private LocalDate fechaLanzamiento;
    private String portadaUrl;

    private List<CancionDTO> canciones;
}
