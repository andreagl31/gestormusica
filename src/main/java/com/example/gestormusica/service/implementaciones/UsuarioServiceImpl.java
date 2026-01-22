package com.example.gestormusica.service.implementaciones;

import com.example.gestormusica.Excepciones.ResourceNotFoundException;
import com.example.gestormusica.dtos.CancionSimpleDTO;
import com.example.gestormusica.dtos.PerfilSimpleDTO;
import com.example.gestormusica.dtos.PlaylistSimpleDTO;
import com.example.gestormusica.dtos.UsuarioDTO;
import com.example.gestormusica.model.Cancion;
import com.example.gestormusica.model.Perfil;
import com.example.gestormusica.model.Playlist;
import com.example.gestormusica.model.Usuario;
import com.example.gestormusica.repository.UsuarioRepository;
import com.example.gestormusica.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    //tiene que instanciar el repositorio para usarlo
    private final UsuarioRepository usuarioRepo;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }
    //dtos privados convertir
    //primero convertimos perfil pk sabemos q usuario necesita un perfil
    private PerfilSimpleDTO toPerfilSimpleDTO(Perfil perfil) {
        if (perfil == null) return null;
        PerfilSimpleDTO dto = new PerfilSimpleDTO();
        dto.setNombre(perfil.getNombre());
        dto.setApellido(perfil.getApellido());
        dto.setFechaNacimiento(perfil.getFechaNacimiento());
        dto.setPais(perfil.getPais());
        return dto;
    }
    //luego convertimos las playlist porque sabemos q el usuario tiene muchas playlist
    private PlaylistSimpleDTO toPlaylistSimpleDTO(Playlist p) {
        PlaylistSimpleDTO dto = new PlaylistSimpleDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        return dto;
    }

    private CancionSimpleDTO toCancionSimpleDTO(Cancion c) {
        CancionSimpleDTO dto = new CancionSimpleDTO();
        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());
        dto.setGenero(c.getGenero());
        return dto;
    }
    // aqui cogemos todos los tros dtos y devolvemos un usuario bien hecho y
    //con todo inicializado
    private UsuarioDTO toUsuarioDTO(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setEmail(u.getEmail());
        dto.setActivo(u.isActivo());
        dto.setFechaRegistro(u.getFechaRegistro());
        dto.setRole(u.getRole());

        dto.setPerfil(toPerfilSimpleDTO(u.getPerfil()));

        if (u.getPlaylists() != null) {
            dto.setPlaylists(u.getPlaylists()
                    .stream()
                    .map(this::toPlaylistSimpleDTO)
                    .collect(Collectors.toSet()));
        }

        if (u.getFavoritas() != null) {
            dto.setFavoritas(u.getFavoritas()
                    .stream()
                    .map(this::toCancionSimpleDTO)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }


    @Override
    public UsuarioDTO crear(Usuario usuario) {
       return toUsuarioDTO(usuarioRepo.save(usuario)) ;
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        return toUsuarioDTO(usuarioRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado")));
    }

    @Override
    public List<UsuarioDTO> listar() {
        return usuarioRepo.findAll().stream().map(this::toUsuarioDTO).toList();
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepo.deleteById(id);
    }

}
