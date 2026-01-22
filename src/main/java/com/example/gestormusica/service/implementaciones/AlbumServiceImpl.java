package com.example.gestormusica.service.implementaciones;

import com.example.gestormusica.Excepciones.ResourceNotFoundException;
import com.example.gestormusica.dtos.AlbumDTO;
import com.example.gestormusica.dtos.CancionDTO;
import com.example.gestormusica.model.Album;
import com.example.gestormusica.model.Cancion;
import com.example.gestormusica.repository.AlbumRepository;
import com.example.gestormusica.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    //conversores privados

    private CancionDTO toCancionDTO(Cancion c) {
        CancionDTO dto = new CancionDTO();
        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());
        dto.setDuracion(c.getDuracion());
        dto.setGenero(c.getGenero());
        dto.setReproducciones(c.getReproducciones());
        dto.setFechaPublicacion(c.getFechaPublicacion());
        return dto;
    }

    private AlbumDTO toAlbumDTO(Album a) {
        AlbumDTO dto = new AlbumDTO();
        dto.setId(a.getId());
        dto.setTitulo(a.getTitulo());
        dto.setGenero(a.getGenero());
        dto.setFechaLanzamiento(a.getFechaLanzamiento());
        dto.setPortadaUrl(a.getPortadaUrl());
        // esto inicializa ya las canciones por lo que nos evitamos
        //inicializarlas con un método
        if (a.getCanciones() != null) {
            dto.setCanciones(
                    a.getCanciones()
                            .stream()
                            .map(this::toCancionDTO)
                            .toList()
            );
        }
        return dto;
    }

    //métodos públicos
    @Override
    public AlbumDTO obtenerPorId(Long id) {
        Album album= albumRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album con ID " + id + " no encontrado"));
        return toAlbumDTO(album);
    }

    @Override
    public List<AlbumDTO> listar() {
        return albumRepo.findAll().stream().map(this::toAlbumDTO).toList();
    }

    @Override
    public void eliminar(Long id) {
        albumRepo.deleteById(id);
    }

    private final AlbumRepository albumRepo;
    public AlbumServiceImpl(AlbumRepository albumRepo) {this.albumRepo = albumRepo;}

    @Override
    public AlbumDTO crear (Album album){
        return toAlbumDTO(albumRepo.save(album));
    }


}

