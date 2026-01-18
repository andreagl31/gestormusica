package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.model.Artista;
import com.example.gestorseries.repository.ArtistaRepository;
import com.example.gestorseries.service.ArtistaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaServiceImpl  implements ArtistaService{
    private final ArtistaRepository artistaRepo;

    public ArtistaServiceImpl(ArtistaRepository artistaRepo) {
        this.artistaRepo = artistaRepo;
    }
    @Override
    public Artista crear(Artista artista) {
        return artistaRepo.save(artista);
    }

    @Override
    public Artista obtenerPorId(Long id) {
        return artistaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista no encontrado"));
    }

    @Override
    public List<Artista> listar() {
        return artistaRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        artistaRepo.deleteById(id);
    }
}

