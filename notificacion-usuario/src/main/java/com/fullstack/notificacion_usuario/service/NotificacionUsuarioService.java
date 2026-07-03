package com.fullstack.notificacion_usuario.service;


import com.fullstack.notificacion_usuario.webClient.UsuarioClient;
import com.fullstack.notificacion_usuario.dto.NotificacionUsuarioRequest;
import com.fullstack.notificacion_usuario.model.NotificacionUsuario;
import com.fullstack.notificacion_usuario.repository.NotificacionUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional

public class NotificacionUsuarioService {

    @Autowired
    private NotificacionUsuarioRepository notificacionUsuarioRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<NotificacionUsuario> getNotificaionUsuario() {return notificacionUsuarioRepository.findAll();}

    public NotificacionUsuario buscarPorId(Integer id){return notificacionUsuarioRepository.findById(id).orElse(null);}

    public NotificacionUsuario guardarNotificacionUsuario(NotificacionUsuario notificacionUsuario) {return notificacionUsuarioRepository.save(notificacionUsuario);}

    public NotificacionUsuario crearDesdeRequest(NotificacionUsuarioRequest request, String token) {
        Map<String, Object> usuario = usuarioClient.obtenerUsuarioId(request.getUsuarioId(), token);
        if (usuario == null || usuario.isEmpty()) throw new RuntimeException("Usuario no encontrado");

        NotificacionUsuario notificacionUsuario = new NotificacionUsuario();

        notificacionUsuario.setUsuarioId(request.getUsuarioId());
        notificacionUsuario.setMensaje(request.getMensaje());
        notificacionUsuario.setLeido(request.getLeido());

        //Client de Usuario
        notificacionUsuario.setUsername(usuario.get("username").toString());



        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    public boolean eliminar(Integer id) {
        NotificacionUsuario notificacionUsuario = buscarPorId(id);
        if(notificacionUsuario == null){
            return false;
        }
        notificacionUsuarioRepository.deleteById(id);
        return true;
    }


}
