package com.fullstack.favorito_juego.dto;

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

public class FavoritoJuegoRequest {

    @NotNull(message = "La id de usuario es obligatoria")
    @Column(nullable = false)
    private Integer usuarioId;

    @NotNull(message = "La id del juego es obligatoria")
    @Column(nullable = false)
    private Integer juegoId;

    @NotBlank(message = "Las horas de juego son obligatorias")
    @Column(nullable = false)
    private Integer horasDeJuego;


}
