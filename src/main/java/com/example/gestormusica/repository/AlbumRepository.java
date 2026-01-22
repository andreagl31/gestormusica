package com.example.gestormusica.repository;

import com.example.gestormusica.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository <Album,Long> {
}
