package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.model.Cancion;
import com.example.gestorseries.repository.CancionRepository;
import com.example.gestorseries.service.CancionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancionServiceImpl implements CancionService {
    private final CancionRepository cancionRepo;

    public CancionServiceImpl(CancionRepository cancionRepo) {
        this.cancionRepo = cancionRepo;
    }

    @Override
    public Cancion crear(Cancion cancion) {
        return cancionRepo.save(cancion);
    }

    @Override
    public Cancion obtenerPorId(Long id) {
        return cancionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Canción no encontrada"));
    }

    @Override
    public List<Cancion> listar() {
        return cancionRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        cancionRepo.deleteById(id);
    }

}
