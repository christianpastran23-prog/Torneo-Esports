package com.fullstack.torneo_patrocinador.repository;

import com.fullstack.torneo_patrocinador.model.TorneoPatrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneoPatrocinadorRepository extends JpaRepository<TorneoPatrocinador, Integer> {
}
