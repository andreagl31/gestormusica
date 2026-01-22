package com.example.gestormusica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name= "playlists")
public class Playlist {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private boolean publica;
    private LocalDate fechaCreacion;
    @ManyToMany(mappedBy = "playlists")
    private Set<Usuario> usuarios = new HashSet<>();
    @ManyToMany(mappedBy = "playlists")
    private Set<Cancion> canciones = new HashSet<>();


}