package com.fullstack.notificacion_usuario.dto;

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

public class NotificacionUsuarioRequest {

    @NotNull(message = "La id de usuario es obligatoria")
    @Column(nullable = false)
    private Integer usuarioId;

    @NotNull(message = "El mensaje no puede estar vacio")
    @Column(nullable = false)
    private String mensaje;

    @NotBlank(message = "La fecha del pedido es obligatoria")
    @Pattern(regexp = "(?i)LEIDO|NO_LEIDO", message = "Solo puede colocar los siguientes estados LEIDO|ENVIADO|NO_LEIDO")
    private String leido;


}
