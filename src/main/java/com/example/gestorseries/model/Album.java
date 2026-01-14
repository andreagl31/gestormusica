package com.example.gestorseries.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name= "albumes")
public class Album {
    @Id
    @GeneratedValue
    private Long id;
    private String titulo;
    private String genero;
    private LocalDate fechaLanzamiento;
    private String portadaUrl;
}
