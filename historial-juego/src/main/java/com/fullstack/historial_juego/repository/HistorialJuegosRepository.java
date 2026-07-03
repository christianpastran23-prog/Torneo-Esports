package com.fullstack.historial_juego.repository;

import com.fullstack.historial_juego.model.HistorialJuego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialJuegosRepository extends JpaRepository<HistorialJuego, Integer> {
}
