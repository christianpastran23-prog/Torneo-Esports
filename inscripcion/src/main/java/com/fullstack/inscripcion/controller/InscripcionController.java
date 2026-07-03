package com.fullstack.inscripcion.controller;

import com.fullstack.inscripcion.dto.InscripcionRequest;
import com.fullstack.inscripcion.model.Inscripcion;
import com.fullstack.inscripcion.service.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")

public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<Inscripcion>> getInscripciones(){
        List<Inscripcion> inscripciones = inscripcionService.getInscripciones();
        if(inscripciones.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inscripciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInscripcionesPorId(@PathVariable Integer id){
        Inscripcion inscripcion = inscripcionService.buscarPorId(id);
        if(inscripcion == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inscripcion);
    }

    @PostMapping
    public ResponseEntity<?> crearInscripciones(@Valid @RequestBody InscripcionRequest request, @RequestHeader("Authorization") String token){
        Inscripcion inscripcionGuardado = inscripcionService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(inscripcionGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInscripcion(@PathVariable Integer id){
        boolean eliminado = inscripcionService.eliminar(id);
        if(!eliminado){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/publico")
    public ResponseEntity<String> publico(){
        return ResponseEntity.ok("Endpoint público - Torneo Esports");
    }

}
