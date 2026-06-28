package com.fullstack.ficha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "ficha_partida")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class FichaPartida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private Integer torneoId;

    @Column(nullable = false)
    private Integer juegoId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer puntaje;

    @Column(nullable = false)
    private String nombreTorneo;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private Integer premioDolares;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private Integer KD;

    @Column(nullable = false)
    private String resultado;

    @Column(nullable = false)
    private Integer puntos;

    @Column(nullable = false)
    private LocalDateTime fechaPartida;





}
