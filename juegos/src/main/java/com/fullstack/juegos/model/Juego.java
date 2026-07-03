package com.fullstack.juegos.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "juegos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "titulo")
    private String titulo;

    @Column(nullable = false, name = "desarrollador")
    private String desarrollador;

    @Column(nullable = false, name = "versionJuego")
    private String versionJuego;

    @Column(nullable = false, name = "categoria")
    private String categoria;

}