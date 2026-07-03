package com.fullstack.favorito_juego.repository;

import com.fullstack.favorito_juego.model.FavoritoJuego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoJuegoRepository extends JpaRepository<FavoritoJuego, Integer> {
}
