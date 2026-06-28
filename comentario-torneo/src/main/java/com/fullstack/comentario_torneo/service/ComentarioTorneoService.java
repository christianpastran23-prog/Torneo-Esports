package com.fullstack.comentario_torneo.service;

import com.fullstack.comentario_torneo.dto.ComentarioTorneoRequest;
import com.fullstack.comentario_torneo.model.ComentarioTorneo;
import com.fullstack.comentario_torneo.repository.ComentarioTorneoRepository;
import com.fullstack.comentario_torneo.webClient.TorneoClient;
import com.fullstack.comentario_torneo.webClient.UsuarioClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional

public class ComentarioTorneoService {

    @Autowired
    private ComentarioTorneoRepository comentarioTorneoRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private TorneoClient torneoClient;

    public List<ComentarioTorneo> getComentarioTorneo() {return comentarioTorneoRepository.findAll();}

    public ComentarioTorneo buscarPorId(Integer id){return comentarioTorneoRepository.findById(id).orElse(null);}

    public ComentarioTorneo guardarComentarioTorneo(ComentarioTorneo comentario) {return comentarioTorneoRepository.save(comentario);}

    public ComentarioTorneo crearDesdeRequest(ComentarioTorneoRequest request, String token) {
        Map<String, Object> usuario = usuarioClient.obtenerUsuarioId(request.getUsuarioId(), token);
        if (usuario == null || usuario.isEmpty()) throw new RuntimeException("Usuario no encontrado");

        Map<String, Object> torneo = torneoClient.obtenerTorneoId(request.getTorneoId(), token);
        if (torneo == null || torneo.isEmpty()) throw new RuntimeException("Torneo no encontrado");

        ComentarioTorneo comentario = new ComentarioTorneo();

        comentario.setUsuarioId(request.getUsuarioId());
        comentario.setTorneoId(request.getTorneoId());
        comentario.setMensajeComentario(request.getMensajeComentario());
        comentario.setUsername(usuario.get("username").toString());
        comentario.setRegion(usuario.get("region").toString());
        comentario.setNombreTorneo(torneo.get("nombreTorneo").toString());

        return comentarioTorneoRepository.save(comentario);
    }

    public boolean eliminar(Integer id) {
        ComentarioTorneo comentario = buscarPorId(id);
        if(comentario == null){
            return false;
        }
        comentarioTorneoRepository.deleteById(id);
        return true;
    }


}
