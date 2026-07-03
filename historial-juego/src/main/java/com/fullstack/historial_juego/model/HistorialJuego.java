package com.fullstack.historial_juego.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "historial")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class HistorialJuego {

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
    private String titulo;

    @Column(nullable = false)
    private int tiempoJugado;

}
