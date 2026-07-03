package com.fullstack.historial_juego.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HistorialJuegoRequest {

    @NotNull(message = "La id de usuario es obligatoria")
    @Column(nullable = false)
    private Integer usuarioId;


    @NotNull(message = "La id del juego es obligatoria")
    @Column(nullable = false)
    private Integer juegoId;

    @NotBlank(message = "El tiempo jugado es obligatorio es obligatoria")
    @Column(nullable = false)
    private Integer tiempoJugado;

}
