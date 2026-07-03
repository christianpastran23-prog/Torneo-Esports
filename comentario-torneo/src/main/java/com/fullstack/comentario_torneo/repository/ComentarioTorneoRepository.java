package com.fullstack.comentario_torneo.repository;

import com.fullstack.comentario_torneo.model.ComentarioTorneo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioTorneoRepository extends JpaRepository<ComentarioTorneo, Integer> {
}
