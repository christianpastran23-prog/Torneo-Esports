package com.fullstack.juegos.repository;

import com.fullstack.juegos.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegosRepository extends JpaRepository<Juego, Integer> {
}
