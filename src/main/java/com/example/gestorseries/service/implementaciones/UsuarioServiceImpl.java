package com.example.gestorseries.service.implementaciones;

import com.example.gestorseries.model.Usuario;
import com.example.gestorseries.repository.UsuarioRepository;
import com.example.gestorseries.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    //tiene que instanciar el repositorio para usarlo
    private final UsuarioRepository usuarioRepo;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }
    @Override
    public Usuario crear(Usuario usuario) {
       return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepo.deleteById(id);
    }
}
