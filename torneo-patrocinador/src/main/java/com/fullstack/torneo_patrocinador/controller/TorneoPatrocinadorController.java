package com.fullstack.torneo_patrocinador.controller;

import com.fullstack.torneo_patrocinador.dto.TorneoPatrocinadorRequest;
import com.fullstack.torneo_patrocinador.model.TorneoPatrocinador;
import com.fullstack.torneo_patrocinador.service.TorneoPatrocinadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torneo-patrocinador")

public class TorneoPatrocinadorController {

    @Autowired
    private TorneoPatrocinadorService torneoPatrocinadorService;

    @GetMapping
    public ResponseEntity<List<TorneoPatrocinador>> getTorneoPatronciador(){
        List<TorneoPatrocinador> patrocinadores = torneoPatrocinadorService.getTorneoPatrocinador();
        if(patrocinadores.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patrocinadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTorneoPatrocinadorPorId(@PathVariable Integer id){
        TorneoPatrocinador patrocinador = torneoPatrocinadorService.buscarPorId(id);
        if(patrocinador == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patrocinador);
    }

    @PostMapping
    public ResponseEntity<?> crearTorneoPatrocinador(@Valid @RequestBody TorneoPatrocinadorRequest request, @RequestHeader("Authorization") String token){
        TorneoPatrocinador torneoPatrocinadorGuardado = torneoPatrocinadorService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(torneoPatrocinadorGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTorneoPatrocinador(@PathVariable Integer id){
        boolean eliminado = torneoPatrocinadorService.eliminar(id);
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
