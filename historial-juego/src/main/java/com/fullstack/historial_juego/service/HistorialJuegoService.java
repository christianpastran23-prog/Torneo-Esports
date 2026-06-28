package com.fullstack.historial_juego.service;

import com.fullstack.historial_juego.dto.HistorialJuegoRequest;
import com.fullstack.historial_juego.model.HistorialJuego;
import com.fullstack.historial_juego.repository.HistorialJuegosRepository;
import com.fullstack.historial_juego.webClient.JuegosClient;
import com.fullstack.historial_juego.webClient.UsuarioClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional

public class HistorialJuegoService {

    @Autowired
    private HistorialJuegosRepository historialJuegosRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private JuegosClient juegosClient;

    public List<HistorialJuego> getHistorialJuego() {return historialJuegosRepository.findAll();}

    public HistorialJuego buscarPorId(Integer id){return historialJuegosRepository.findById(id).orElse(null);}

    public HistorialJuego guardarHistorialJuego(HistorialJuego historial) {return historialJuegosRepository.save(historial);}

    public HistorialJuego crearDesdeRequest(HistorialJuegoRequest request, String token) {
        Map<String, Object> usuario = usuarioClient.obtenerUsuarioId(request.getUsuarioId(), token);
        if (usuario == null || usuario.isEmpty()) throw new RuntimeException("Usuario no encontrado");

        Map<String, Object> juego = juegosClient.obtenerJuegosId(request.getJuegoId(), token);
        if(juego == null || juego.isEmpty()) throw new RuntimeException("Juego no encontrado");

        HistorialJuego historial = new HistorialJuego();

        historial.setUsuarioId(request.getUsuarioId());
        historial.setJuegoId(request.getJuegoId());
        historial.setTiempoJugado(request.getTiempoJugado());
        historial.setUsername(usuario.get("username").toString());
        historial.setTitulo(juego.get("titulo").toString());

        return historialJuegosRepository.save(historial);
    }

    public boolean eliminar(Integer id) {
        HistorialJuego historial = buscarPorId(id);
        if(historial == null){
            return false;
        }
        historialJuegosRepository.deleteById(id);
        return true;
    }


}
