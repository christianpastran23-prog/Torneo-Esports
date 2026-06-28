package com.fullstack.favorito_juego.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "favoritos")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class FavoritoJuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private Integer juegoId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String versionJuego;

    @Column(nullable = false)
    private String desarrollador;

    @Column(nullable = false)
    private Integer horasDeJuego;




}
