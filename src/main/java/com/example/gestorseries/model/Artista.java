package com.example.gestorseries.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name= "artistas")
public class Artista {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String pais;
    private LocalDate fecha;
    private String biografia;




}
