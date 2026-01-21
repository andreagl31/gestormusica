package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.Excepciones.ResourceNotFoundException;
import com.example.gestorseries.dtos.ArtistaDTO;
import com.example.gestorseries.dtos.CancionDTO;
import com.example.gestorseries.dtos.CancionSimpleDTO;
import com.example.gestorseries.model.Artista;
import com.example.gestorseries.model.Cancion;
import com.example.gestorseries.repository.CancionRepository;
import com.example.gestorseries.service.CancionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CancionServiceImpl implements CancionService {
    //Incicializo los dtos

    //necesito una canción para controlar las canciones que
    //tiene cada artista
    private CancionDTO toCancionDTO(Cancion c) {
        CancionDTO dto = new CancionDTO();
        dto.setId(c.getId());
        dto.setTitulo(c.getTitulo());
        dto.setGenero(c.getGenero());
        dto.setDuracion(c.getDuración());
        dto.setReproducciones(c.getReproduccioes());
        dto.setFechaPublicacion(c.getFechaPublicacion());
        return dto;
    }


    private final CancionRepository cancionRepo;

    public CancionServiceImpl(CancionRepository cancionRepo) {
        this.cancionRepo = cancionRepo;
    }

    @Override
    public CancionDTO crear(Cancion cancion) {
        return toCancionDTO(cancionRepo.save(cancion));
    }

    @Override
    public CancionDTO obtenerPorId(Long id) {
        return toCancionDTO(cancionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Canción con ID " + id + " no encontrado")));
    }

    @Override
    public List<CancionDTO> listar() {
        return cancionRepo.findAll().stream().map(this::toCancionDTO).toList();
    }

    @Override
    public void eliminar(Long id) {
        cancionRepo.deleteById(id);
    }

}
