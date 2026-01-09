package com.ejemplofull.backendfull.dto;

import lombok.Data;

@Data
public class UsuarioCreateDTO {
    private String nombre;
    private String email;
    private String password;
}
