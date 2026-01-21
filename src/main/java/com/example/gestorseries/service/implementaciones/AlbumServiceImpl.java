package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.Excepciones.ResourceNotFoundException;
import com.example.gestorseries.dtos.AlbumDTO;
import com.example.gestorseries.dtos.CancionDTO;
import com.example.gestorseries.model.Album;
import com.example.gestorseries.model.Cancion;
import com.example.gestorseries.repository.AlbumRepository;
import com.example.gestorseries.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    //conversores privados

    private CancionDTO toCancionDTO(Cancion c) {
        CancionDTO dto = new CancionDTO();
        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());
        dto.setDuracion(c.getDuración());
        dto.setGenero(c.getGenero());
        dto.setReproducciones(c.getReproduccioes());
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

