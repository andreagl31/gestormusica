package com.example.gestorseries.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

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

    //1:n cancion album
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "album_id",nullable = false)
    @JsonBackReference //para serializar la lista
    private Album album;
    //n:m cancion playlist
    @ManyToMany
    @JoinTable (name="playlist_cancion",
    joinColumns = @JoinColumn(name = "cancion_id"),
    inverseJoinColumns = @JoinColumn (name="playlist_id"))
    private Set<Playlist> playlists;
    //n:m cancion artistas
    @ManyToMany (mappedBy = "canciones")
    private Set <Artista> artistas;
    //n:m cancion favoritas
    @ManyToMany (mappedBy = "favoritas")
    private Set <Usuario> usuarioFavoritos;






}
