package com.fullstack.notificacion_usuario.repository;

import com.fullstack.notificacion_usuario.model.NotificacionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionUsuarioRepository extends JpaRepository<NotificacionUsuario, Integer> {
}
