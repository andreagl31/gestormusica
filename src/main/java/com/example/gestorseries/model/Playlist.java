package com.example.gestorseries.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
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

}
