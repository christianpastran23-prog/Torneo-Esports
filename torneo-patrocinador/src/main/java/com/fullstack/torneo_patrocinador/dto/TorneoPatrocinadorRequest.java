package com.fullstack.torneo_patrocinador.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TorneoPatrocinadorRequest {

    @NotNull(message = "La id de torneo es obligatoria")
    @Column(nullable = false)
    private Integer torneoId;

    @NotNull(message = "La id del nombreEmpresa es obligatoria")
    @Column(nullable = false)
    private String nombreEmpresa;

    @NotNull(message = "La id del contacto es obligatoria")
    @Column(nullable = false)
    private String contacto;

}
