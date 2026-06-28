package com.fullstack.notificacion_usuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "notificaciones")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class NotificacionUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String leido;

}
