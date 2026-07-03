package com.fullstack.inscripcion.repository;

import com.fullstack.inscripcion.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
}
