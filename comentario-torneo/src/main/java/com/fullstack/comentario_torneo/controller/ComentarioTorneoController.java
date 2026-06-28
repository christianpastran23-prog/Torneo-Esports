package com.fullstack.comentario_torneo.controller;

import com.fullstack.comentario_torneo.dto.ComentarioTorneoRequest;
import com.fullstack.comentario_torneo.model.ComentarioTorneo;
import com.fullstack.comentario_torneo.service.ComentarioTorneoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentario-torneos")

public class ComentarioTorneoController {

    @Autowired
    private ComentarioTorneoService comentarioTorneoService;

    @GetMapping
    public ResponseEntity<List<ComentarioTorneo>> getComentarioTorneo(){
        List<ComentarioTorneo> comentario = comentarioTorneoService.getComentarioTorneo();
        if(comentario.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComentarioTorneoPorId(@PathVariable Integer id){
        ComentarioTorneo comentario = comentarioTorneoService.buscarPorId(id);
        if(comentario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comentario);
    }

    @PostMapping
    public ResponseEntity<?> crearComentarioTorneo(@Valid @RequestBody ComentarioTorneoRequest request, @RequestHeader("Authorization") String token){
        ComentarioTorneo comentarioTorneoGuardado = comentarioTorneoService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioTorneoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInscripcion(@PathVariable Integer id){
        boolean eliminado = comentarioTorneoService.eliminar(id);
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
