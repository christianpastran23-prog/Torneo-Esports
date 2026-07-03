package com.fullstack.torneo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorneoRequest {

    @NotBlank(message = "El nombre del torneo es obligatorio.")
    @Size(max = 100, message = "El nombre del torneo no debe superar los 100 caracteres.")
    private String nombreTorneo;

    @NotNull(message = "La fecha no puede estar vacía")
    private LocalDateTime fechaInicio;

    @NotBlank(message = "El estado del torneo es obligatorio.")
    @Pattern(regexp = "(?i)CREADO|INSCRIPCIONES_ABIERTAS|EN_PROGRESO|FINALIZADO", message = "Debe colocar el estado del torneo CREADO, INSCRIPCIONES_ABIERTAS, EN_PROGRESO o FINALIZADO ")
    private String estado;

    @NotNull(message = "El premio no puede estar vacio")
    private Integer premioDolares;


}
