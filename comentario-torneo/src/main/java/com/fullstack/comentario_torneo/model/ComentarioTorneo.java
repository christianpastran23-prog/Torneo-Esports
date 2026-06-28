package com.fullstack.comentario_torneo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "comentarios")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class ComentarioTorneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private Integer torneoId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String mensajeComentario;

    @Column(nullable = false)
    private String nombreTorneo;

}
