package com.fullstack.torneo.controller;

import com.fullstack.torneo.dto.TorneoRequest;
import com.fullstack.torneo.model.Torneo;
import com.fullstack.torneo.service.TorneoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @GetMapping
    public ResponseEntity<List<Torneo>> listar(){
        List<Torneo> torneos = torneoService.listarTodos();
        if(torneos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(torneos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Torneo> buscarPorId(@PathVariable Integer id){
        Torneo productos = torneoService.buscarPorId(id);
        if(productos == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Torneo> guardar(@Valid @RequestBody TorneoRequest request){
        Torneo torneoGuardado = torneoService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(torneoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Torneo> actualizar(@PathVariable Integer id, @Valid @RequestBody TorneoRequest request){
        Torneo torneoActualizado = torneoService.actualizar(id, request);
        if(torneoActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(torneoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        boolean eliminado = torneoService.eliminar(id);
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
