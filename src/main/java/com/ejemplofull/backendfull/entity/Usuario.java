package com.ejemplofull.backendfull.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String nombre;
    
}
