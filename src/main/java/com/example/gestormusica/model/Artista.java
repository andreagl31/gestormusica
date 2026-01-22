package com.example.gestormusica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

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
    @ManyToMany
    @JoinTable(
            name = "artista_cancion",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "cancion_id")
    )
    private Set<Cancion> canciones;




}
