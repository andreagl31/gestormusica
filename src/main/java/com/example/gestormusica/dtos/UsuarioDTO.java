package com.example.gestormusica.dtos;

import com.example.gestormusica.model.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UsuarioDTO {
    private Long id;
    private String username;
    private boolean activo;
    private LocalDate fechaRegistro;
    private String email;
    private Role role;

    private PerfilSimpleDTO perfil;
    private Set<PlaylistSimpleDTO> playlists; //lista de los ids de las playlist
    private Set<CancionSimpleDTO> favoritas; //lista de los ids de las favoritas
}
