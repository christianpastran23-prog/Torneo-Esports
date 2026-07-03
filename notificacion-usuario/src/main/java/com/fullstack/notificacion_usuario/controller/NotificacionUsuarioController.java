package com.fullstack.notificacion_usuario.controller;

import com.fullstack.notificacion_usuario.dto.NotificacionUsuarioRequest;
import com.fullstack.notificacion_usuario.model.NotificacionUsuario;
import com.fullstack.notificacion_usuario.service.NotificacionUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacion-usuario")

public class NotificacionUsuarioController {

    @Autowired
    private NotificacionUsuarioService notificacionUsuarioService;

    @GetMapping
    public ResponseEntity<List<NotificacionUsuario>> getNotificacionUsuario(){
        List<NotificacionUsuario> notificacionUsuarios = notificacionUsuarioService.getNotificaionUsuario();
        if(notificacionUsuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificacionUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificacionUsuarioPorId(@PathVariable Integer id){
        NotificacionUsuario notificacionUsuario = notificacionUsuarioService.buscarPorId(id);
        if(notificacionUsuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificacionUsuario);
    }

    @PostMapping
    public ResponseEntity<?> crearNotificacionUsuario(@Valid @RequestBody NotificacionUsuarioRequest request, @RequestHeader("Authorization") String token){
        NotificacionUsuario notificacionUsuarioGuardado = notificacionUsuarioService.crearDesdeRequest(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionUsuarioGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarNotificacionUsuario(@PathVariable Integer id){
        boolean eliminado = notificacionUsuarioService.eliminar(id);
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
