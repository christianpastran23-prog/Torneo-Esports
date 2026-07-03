package com.fullstack.comentario_torneo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ComentarioTorneoRequest {

    @NotNull(message = "La id de usuario es obligatoria")
    @Column(nullable = false)
    private Integer usuarioId;

    @NotNull(message = "La id del torneo es obligatoria")
    @Column(nullable = false)
    private Integer torneoId;

    @NotBlank(message = "El mensaje o comentario es obligatorio")
    @Column(nullable = false)
    private String mensajeComentario;







}
