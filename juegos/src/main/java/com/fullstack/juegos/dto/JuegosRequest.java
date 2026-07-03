package com.fullstack.juegos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JuegosRequest {

    @NotBlank(message = "El  titulo del juego es obligatorio.")
    @Size(max = 100, message = "El titulo del juego no debe superar los 100 caracteres.")
    private String titulo;

    @NotNull(message = "El desarrollador no puede estar vacio")
    private String desarrollador;

    @NotBlank(message = "La version del juego no puede estar vacia")
    private String versionJuego;

    @NotNull(message = "La categoria del juego no puede estar vacio")
    private String categoria;


}
