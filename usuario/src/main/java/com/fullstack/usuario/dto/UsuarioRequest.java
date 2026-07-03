package com.fullstack.usuario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 150, message = "El nombre no debe superar los 150 caracteres")
    private String username;


    @NotBlank(message = "La region es obligatoria")
    @Pattern(regexp = "(?i)EU|LAT|NORT|AS", message = "Debe colocar region EU, LAT, NORT o AS")
    private String region;

    @NotNull(message = "El correo es obligatorio")
    @Email(message = "El email debe tener este tipo de formato nombre@gmail.com")
    private String email;

    @NotNull(message = "El puntaje no puede estar vacio")
    private Integer puntaje;



}
