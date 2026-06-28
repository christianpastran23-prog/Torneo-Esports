package com.fullstack.ficha.controller;

import com.fullstack.ficha.dto.FichaPartidaRequest;
import com.fullstack.ficha.model.FichaPartida;
import com.fullstack.ficha.service.FichasPartidasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ficha_partidas")

public class FichaPartidasController {

    @Autowired
    private FichasPartidasService fichasPartidasService;

    @GetMapping
    public ResponseEntity<List<FichaPartida>> getFichas(){
        List<FichaPartida> fichasPartidas = fichasPartidasService.getInscripciones();
        if(fichasPartidas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fichasPartidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFichasPorId(@PathVariable Integer id){
        FichaPartida fichaPartida = fichasPartidasService.buscarPorId(id);
        if(fichaPartida == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fichaPartida);
    }

    @PostMapping
    public ResponseEntity<?> crearFichas(@Valid @RequestBody FichaPartidaRequest request, @RequestHeader("Authorization") String token){
        FichaPartida fichaPartidaGuardado = fichasPartidasService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaPartidaGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFicha(@PathVariable Integer id){
        boolean eliminado = fichasPartidasService.eliminar(id);
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
