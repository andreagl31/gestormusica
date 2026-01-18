package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.model.Album;
import com.example.gestorseries.repository.AlbumRepository;
import com.example.gestorseries.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Override
    public Album obtenerPorId(Long id) {
        return albumRepo.findById(id).orElse(null);
    }

    @Override
    public List<Album> listar() {
        return albumRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        albumRepo.deleteById(id);
    }

    private final AlbumRepository albumRepo;
    public AlbumServiceImpl(AlbumRepository albumRepo) {this.albumRepo = albumRepo;}

    @Override
    public Album crear (Album album){
        return albumRepo.save(album);
    }
}

