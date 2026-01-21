package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.Excepciones.ResourceNotFoundException;
import com.example.gestorseries.dtos.ArtistaDTO;
import com.example.gestorseries.dtos.CancionSimpleDTO;
import com.example.gestorseries.model.Artista;
import com.example.gestorseries.model.Cancion;
import com.example.gestorseries.repository.ArtistaRepository;
import com.example.gestorseries.service.ArtistaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistaServiceImpl  implements ArtistaService{
    //Inicializar dtos
    //necesito una canción para controlar las canciones que
    //tiene cada artista
    private CancionSimpleDTO toCancionSimpleDTO(Cancion c) {
        CancionSimpleDTO dto = new CancionSimpleDTO();
        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());
        dto.setGenero(c.getGenero());
        return dto;
    }
    private ArtistaDTO toArtistaDTO(Artista a) {
        ArtistaDTO dto = new ArtistaDTO();
        dto.setId(a.getId());
        dto.setNombre(a.getNombre());

        if (a.getCanciones() != null) {
            dto.setCanciones(
                    a.getCanciones()
                            .stream()
                            .map(this::toCancionSimpleDTO)
                            .collect(Collectors.toSet())
            );
        }

        return dto;
    }

//métodos públicos
    private final ArtistaRepository artistaRepo;

    public ArtistaServiceImpl(ArtistaRepository artistaRepo) {
        this.artistaRepo = artistaRepo;
    }
    @Override
    public ArtistaDTO crear(Artista artista) {
        return toArtistaDTO(artistaRepo.save(artista));
    }

    @Override
    public ArtistaDTO obtenerPorId(Long id) {
        return crear(artistaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artista con ID " + id + " no encontrado")));
    }

    @Override
    public List<ArtistaDTO> listar() {
        return artistaRepo.findAll().stream().map(this::toArtistaDTO).toList();
    }

    @Override
    public void eliminar(Long id) {
        artistaRepo.deleteById(id);
    }
    // las canciones no se cargan hasta que alguien llame a getcanciones

}

