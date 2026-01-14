package com.example.gestorseries.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table (name = "usuarios") //no es necesario si tiene el mismo nombre q la clase
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean activo;
    private LocalDate fechaRegistro;
    private String email;
    //configuración clave para la entidad inversa:
    //usuario es el atribto que está en la otra clase
    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private Role role;
    // 1:1 con PERFIL (este es el lado inverso)
    @OneToOne (mappedBy = "usuario",cascade = CascadeType.ALL,orphanRemoval = true,fetch =FetchType.LAZY )
    private Perfil perfil;
    //cada clase tiene la otra como atributo en bideireccional

}
