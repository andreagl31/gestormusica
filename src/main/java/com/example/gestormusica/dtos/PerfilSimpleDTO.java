package com.example.gestormusica.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PerfilSimpleDTO {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String pais;
}

