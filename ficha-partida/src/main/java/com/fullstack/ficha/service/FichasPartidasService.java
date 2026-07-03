package com.fullstack.ficha.service;

import com.fullstack.ficha.dto.FichaPartidaRequest;
import com.fullstack.ficha.model.FichaPartida;
import com.fullstack.ficha.repository.FichaPartidaRepository;
import com.fullstack.ficha.webClient.JuegosClient;
import com.fullstack.ficha.webClient.UsuarioClient;
import com.fullstack.ficha.webClient.TorneoClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional

public class FichasPartidasService {

    @Autowired
    private FichaPartidaRepository fichaPartidaRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private TorneoClient torneoClient;

    @Autowired
    private JuegosClient juegosClient;

    public List<FichaPartida> getInscripciones() {return fichaPartidaRepository.findAll();}

    public FichaPartida buscarPorId(Integer id){return fichaPartidaRepository.findById(id).orElse(null);}

    public FichaPartida guardarInscripcion(FichaPartida fichaPartida) {return fichaPartidaRepository.save(fichaPartida);}

    public FichaPartida crearDesdeRequest(FichaPartidaRequest request, String token) {
        Map<String, Object> usuario = usuarioClient.obtenerUsuarioId(request.getUsuarioId(), token);
        if (usuario == null || usuario.isEmpty()) throw new RuntimeException("Usuario no encontrado");

        Map<String, Object> torneo = torneoClient.obtenerTorneoId(request.getTorneoId(), token);
        if (torneo == null || torneo.isEmpty()) throw new RuntimeException("Torneo no encontrado");

        Map<String, Object> juego = juegosClient.obtenerJuegosId(request.getJuegoId(), token);
        if(juego == null || juego.isEmpty()) throw new RuntimeException("Juego no encontrado");

        FichaPartida fichaPartida2 = new FichaPartida();

        fichaPartida2.setUsuarioId(request.getUsuarioId());
        fichaPartida2.setTorneoId(request.getTorneoId());
        fichaPartida2.setJuegoId(request.getJuegoId());
        fichaPartida2.setKD(request.getKD());
        fichaPartida2.setResultado(request.getResultado());
        fichaPartida2.setPuntos(request.getPuntos());
        fichaPartida2.setFechaPartida(request.getFechaPartida());

        //Client de Usuario
        fichaPartida2.setUsername(usuario.get("username").toString());
        fichaPartida2.setEmail(usuario.get("email").toString());
        fichaPartida2.setPuntaje(Integer.parseInt(usuario.get("puntaje").toString()));
        fichaPartida2.setRegion(usuario.get("region").toString());
        //Client de Torneo
        fichaPartida2.setNombreTorneo(torneo.get("nombreTorneo").toString());
        fichaPartida2.setEstado(torneo.get("estado").toString());

        // Convertimos el String del mapa a LocalDateTime usando el formato ISO estándar
        LocalDateTime fechaTorneo = LocalDateTime.parse(torneo.get("fechaInicio").toString());

// Se lo asignas a tu objeto de respuesta (DTO), NO a la entidad de la base de datos
        fichaPartida2.setFechaInicio(fechaTorneo);
        fichaPartida2.setPremioDolares(Integer.parseInt(torneo.get("premioDolares").toString()));

        //Client de Juego
        fichaPartida2.setTitulo(juego.get("titulo").toString());
        fichaPartida2.setCategoria(juego.get("categoria").toString());

        return fichaPartidaRepository.save(fichaPartida2);
    }

    public boolean eliminar(Integer id) {
        FichaPartida fichaPartida = buscarPorId(id);
        if(fichaPartida == null){
            return false;
        }
        fichaPartidaRepository.deleteById(id);
        return true;
    }


}
