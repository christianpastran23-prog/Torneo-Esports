package com.fullstack.ficha.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FichaPartidaRequest {

    @NotNull(message = "La id de usuario es obligatoria")
    @Column(nullable = false)
    private Integer usuarioId;

    @NotNull(message = "La id del torneo es obligatoria")
    @Column(nullable = false)
    private Integer torneoId;

    @NotNull(message = "La id del juego es obligatoria")
    @Column(nullable = false)
    private Integer juegoId;

    @NotNull(message = "El KD no puede estar vacio")
    @Column(nullable = false)
    private Integer KD;

    @NotBlank(message = "El resultado no puede estar vacio")
    @Column(nullable = false)
    @Pattern(regexp = "(?i)VICTORIA|DERROTA", message = "Debe colocar si es DERROTA o VICTORIA")
    private String resultado;

    @NotNull(message = "Los puntos no pueden estar vacios")
    @Column(nullable = false)
    private Integer puntos;

    @NotNull(message = "La fecha de la partida no puede estar vacia")
    @Column(nullable = false)
    private LocalDateTime fechaPartida;


}
