package com.fullstack.torneo_patrocinador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "patrocinadores")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class TorneoPatrocinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer torneoId;

    @Column(nullable = false)
    private String nombreEmpresa;

    @Column(nullable = false)
    private String contacto;

    @Column(nullable = false)
    private String nombreTorneo;


}
