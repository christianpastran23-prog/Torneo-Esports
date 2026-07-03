package com.fullstack.favorito_juego.controller;

import com.fullstack.favorito_juego.dto.FavoritoJuegoRequest;
import com.fullstack.favorito_juego.model.FavoritoJuego;
import com.fullstack.favorito_juego.service.FavoritoJuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorito-juegos")

public class FavoritoJuegoController {

    @Autowired
    private FavoritoJuegoService favoritoJuegoService;

    @GetMapping
    public ResponseEntity<List<FavoritoJuego>> getFavoritoJuego(){
        List<FavoritoJuego> favorito = favoritoJuegoService.getFavoritoJuego();
        if(favorito.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(favorito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFavoritoJuegoPorId(@PathVariable Integer id){
        FavoritoJuego favorito = favoritoJuegoService.buscarPorId(id);
        if(favorito == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(favorito);
    }

    @PostMapping
    public ResponseEntity<?> crearFavoritoJuego(@Valid @RequestBody FavoritoJuegoRequest request, @RequestHeader("Authorization") String token){
        FavoritoJuego favoritoJuegoGuardado = favoritoJuegoService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoJuegoGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFavoritoJuego(@PathVariable Integer id){
        boolean eliminado = favoritoJuegoService.eliminar(id);
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
