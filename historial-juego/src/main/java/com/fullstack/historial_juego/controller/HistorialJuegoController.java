package com.fullstack.historial_juego.controller;

import com.fullstack.historial_juego.dto.HistorialJuegoRequest;
import com.fullstack.historial_juego.model.HistorialJuego;
import com.fullstack.historial_juego.service.HistorialJuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-juegos")

public class HistorialJuegoController {

    @Autowired
    private HistorialJuegoService historialJuegoService;

    @GetMapping
    public ResponseEntity<List<HistorialJuego>> getHistorialJuego(){
        List<HistorialJuego> historial = historialJuegoService.getHistorialJuego();
        if(historial.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHistorialJuegoPorId(@PathVariable Integer id){
        HistorialJuego historial = historialJuegoService.buscarPorId(id);
        if(historial == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historial);
    }

    @PostMapping
    public ResponseEntity<?> crearHistorialJuego(@Valid @RequestBody HistorialJuegoRequest request, @RequestHeader("Authorization") String token){
        HistorialJuego historialJuegoGuardado = historialJuegoService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(historialJuegoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHistorialJuego(@PathVariable Integer id){
        boolean eliminado = historialJuegoService.eliminar(id);
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
