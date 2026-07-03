package com.fullstack.juegos.controller;

import com.fullstack.juegos.dto.JuegosRequest;
import com.fullstack.juegos.model.Juego;
import com.fullstack.juegos.service.JuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/juegos")
public class JuegosController {

    @Autowired
    private JuegoService juegoService;

    @GetMapping
    public ResponseEntity<List<Juego>> listar(){
        List<Juego> juegos = juegoService.listarTodos();
        if(juegos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(juegos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juego> buscarPorId(@PathVariable Integer id){
        Juego productos = juegoService.buscarPorId(id);
        if(productos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Juego> guardar(@Valid @RequestBody JuegosRequest request){
        Juego juegoGuardado = juegoService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(juegoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Juego> actualizar(@PathVariable Integer id, @Valid @RequestBody JuegosRequest request){
        Juego juegoActualizado = juegoService.actualizar(id, request);
        if(juegoActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(juegoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        boolean eliminado = juegoService.eliminar(id);
        if (!eliminado){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/publico")
    public ResponseEntity<String> publico(){
        return ResponseEntity.ok("Endpoint público - Torneo Esports");
    }

}
