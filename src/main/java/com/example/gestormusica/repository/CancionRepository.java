package com.example.gestormusica.repository;

import com.example.gestormusica.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository <Cancion,Long> {


}
