package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.model.Perfil;
import com.example.gestorseries.repository.PerfilRepository;
import com.example.gestorseries.service.PerfilService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {
    private final PerfilRepository perfilRepo;

    public PerfilServiceImpl(PerfilRepository perfilRepo) {
        this.perfilRepo = perfilRepo;
    }

    @Override
    public Perfil crear(Perfil perfil) {
        return perfilRepo.save(perfil);
    }

    @Override
    public Perfil obtenerPorId(Long id) {
        return  perfilRepo.findById(id).orElse(null);
    }

    @Override
    public List<Perfil> listar() {
        return perfilRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
         perfilRepo.deleteById(id);
    }
}
