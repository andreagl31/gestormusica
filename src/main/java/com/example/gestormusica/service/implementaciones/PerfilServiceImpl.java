package com.example.gestormusica.service.implementaciones;

import com.example.gestormusica.Excepciones.ResourceNotFoundException;
import com.example.gestormusica.dtos.PerfilDTO;
import com.example.gestormusica.model.Perfil;
import com.example.gestormusica.repository.PerfilRepository;
import com.example.gestormusica.service.PerfilService;
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
