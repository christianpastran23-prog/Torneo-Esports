package com.fullstack.inscripcion.dto;

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

public class InscripcionRequest {

    @NotNull(message = "La id de usuario es obligatoria")
    @Column(nullable = false)
    private Integer usuarioId;

    @NotNull(message = "La id del torneo es obligatoria")
    @Column(nullable = false)
    private Integer torneoId;

    @NotNull(message = "La id del juego es obligatoria")
    @Column(nullable = false)
    private Integer juegoId;

    @NotBlank(message = "El rol del participante es obligatoria")
    @Column(nullable = false)
    private String rolEquipo;

    @NotNull(message = "La fecha de inscripcion no puede estar vacia")
    @Column(nullable = false)
    private LocalDateTime fechaInscripcion;

    @NotNull(message = "La fecha del pedido es obligatoria")
    @Column(nullable = false)
    private Boolean cupoConfirmado;


}
