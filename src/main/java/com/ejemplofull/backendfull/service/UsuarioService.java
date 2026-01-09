package com.ejemplofull.backendfull.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ejemplofull.backendfull.dto.UsuarioCreateDTO;
import com.ejemplofull.backendfull.dto.UsuarioDTO;
import com.ejemplofull.backendfull.entity.Usuario;
import com.ejemplofull.backendfull.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    // CREATE
    public UsuarioDTO crear(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());

        usuario.setPassword(
                passwordEncoder.encode(dto.getPassword()));

        Usuario guardado = usuarioRepository.save(usuario);

        return toDTO(guardado);
    }

    // GET
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // LOGIN
    public UsuarioDTO login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales invalidas"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return toDTO(usuario);
    }

    // UPDATE
    public UsuarioDTO actualizar(Long id, UsuarioCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return toDTO(usuarioRepository.save(usuario));
    }

    // DELETE
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // MAPEAR
    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
