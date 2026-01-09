package com.ejemplofull.backendfull.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplofull.backendfull.dto.UsuarioCreateDTO;
import com.ejemplofull.backendfull.dto.UsuarioDTO;
import com.ejemplofull.backendfull.dto.UsuarioLoginDTO;
import com.ejemplofull.backendfull.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioDTO crear(@RequestBody UsuarioCreateDTO dto) {
        return usuarioService.crear(dto);
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listar();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioLoginDTO dto) {
        try {
            UsuarioDTO usuario = usuarioService.login(dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok(usuario); // 200
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401
        }
    }

    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id, @RequestBody UsuarioCreateDTO dto) {
        return usuarioService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
