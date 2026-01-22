package com.example.gestormusica.repository;

import com.example.gestormusica.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository <Playlist,Long> {
}
