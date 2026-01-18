package com.example.gestorseries.repository;

import com.example.gestorseries.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//los jpa repositorios ya tienen métodos listos como save,findbyid findall, deleteby id por eso si llamas save funciona
//los repositorios tienen que ser una interfaz porque spring data crea la implementación internamente
//automáticamente añadiendo métodos como los métodos listos nombrados arriba
//es una interfaz porque defines q métodos quieres a parte de los que ya implementa spring
public interface UsuarioRepository extends JpaRepository <Usuario,Long> {
    Optional<Usuario> findByUsername (String username);
}
