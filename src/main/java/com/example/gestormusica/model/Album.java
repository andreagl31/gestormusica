package com.example.gestormusica.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

    //Un albúm tiene muchas canciones
    @OneToMany(
            mappedBy="album",
            cascade= CascadeType.ALL,
            orphanRemoval = true

    )
    @JsonManagedReference
    private List<Cancion> canciones;
}
