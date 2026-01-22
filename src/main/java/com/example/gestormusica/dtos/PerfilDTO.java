package com.example.gestormusica.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PerfilDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String pais;
    private String biografia;
}
