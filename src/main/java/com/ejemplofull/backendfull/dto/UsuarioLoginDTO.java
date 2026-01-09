package com.ejemplofull.backendfull.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioLoginDTO {
    private String email;
    private String password;
}
