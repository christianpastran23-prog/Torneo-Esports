package com.fullstack.inscripcion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "inscripciones")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Inscripcion {

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
    private String region;

    @Column(nullable = false)
    private String nombreTorneo;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String versionJuego;

    @Column(nullable = false)
    private String desarrollador;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String rolEquipo;

    @Column(nullable = false)
    private LocalDateTime fechaInscripcion;

    @Column(nullable = false)
    private Boolean cupoConfirmado;





}
