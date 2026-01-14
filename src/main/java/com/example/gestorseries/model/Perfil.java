package com.example.gestorseries.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String pais;
    private String biografia;
    ;
    //Lado inverso
    // Configuración clave para el dueño de la relación:
    @OneToOne(fetch = FetchType.LAZY) //usar lazy para mejorar rendimiento,no carga la otra tabla a on ser que tengas un get
    //por defecto es eager
    @JoinColumn(name = "usuario_id", unique = true, nullable = false) //se pone enel lado de la clase dueño, es la clave foranea de otra tabla
    private Usuario usuario;

}