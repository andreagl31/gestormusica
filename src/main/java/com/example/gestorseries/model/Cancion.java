package com.example.gestorseries.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name= "canciones")
public class Cancion {
    @Id
    @GeneratedValue
    private Long id;
    private String titulo;
    private int duración;
    private String genero;
    private long reproduccioes;
    private LocalDate fechaPublicacion;



}
