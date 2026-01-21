package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.Excepciones.ResourceNotFoundException;
import com.example.gestorseries.dtos.PerfilDTO;
import com.example.gestorseries.model.Perfil;
import com.example.gestorseries.repository.PerfilRepository;
import com.example.gestorseries.service.PerfilService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {
    //CONVERSOR A DTO
    private PerfilDTO toPerfilDTO(Perfil perfil) {
        PerfilDTO dto = new PerfilDTO();
        dto.setId(perfil.getId());
        dto.setApellido(perfil.getApellido());
        dto.setBiografia(perfil.getBiografia());
        dto.setFechaNacimiento(perfil.getFechaNacimiento());
        dto.setPais(perfil.getPais());
        return dto;
    }

    private final PerfilRepository perfilRepo;

    public PerfilServiceImpl(PerfilRepository perfilRepo) {
        this.perfilRepo = perfilRepo;
    }

    @Override
    public PerfilDTO crear(Perfil perfil) {
        return toPerfilDTO(perfilRepo.save(perfil));
    }

    @Override
    public PerfilDTO obtenerPorId(Long id) {
        return toPerfilDTO(perfilRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id " + id)));
    }

    @Override
    public List<PerfilDTO> listar() {
        return perfilRepo.findAll().stream().map(this::toPerfilDTO).toList();
    }

    @Override
    public void eliminar(Long id) {
         perfilRepo.deleteById(id);
    }
}
