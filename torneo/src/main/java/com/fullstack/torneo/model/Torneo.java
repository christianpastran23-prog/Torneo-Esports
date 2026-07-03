package com.fullstack.torneo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Table(name = "torneos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "nombre_torneo")
    private String nombreTorneo;

    @Column(nullable = false, name = "fecha_inicio")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaInicio;

    @Column(nullable = false, name = "estado")
    private String estado;

    @Column(nullable = false, name = "premio_dolares")
    private Integer premioDolares;

}