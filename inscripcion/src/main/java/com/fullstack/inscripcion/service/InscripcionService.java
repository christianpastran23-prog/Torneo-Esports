package com.fullstack.inscripcion.service;

import com.fullstack.inscripcion.dto.InscripcionRequest;
import com.fullstack.inscripcion.model.Inscripcion;
import com.fullstack.inscripcion.repository.InscripcionRepository;
import com.fullstack.inscripcion.webClient.JuegosClient;
import com.fullstack.inscripcion.webClient.UsuarioClient;
import com.fullstack.inscripcion.webClient.TorneoClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional

public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private TorneoClient torneoClient;

    @Autowired
    private JuegosClient juegosClient;

    public List<Inscripcion> getInscripciones() {return inscripcionRepository.findAll();}

    public Inscripcion buscarPorId(Integer id){return inscripcionRepository.findById(id).orElse(null);}

    public Inscripcion guardarInscripcion(Inscripcion inscripcion) {return inscripcionRepository.save(inscripcion);}

    public Inscripcion crearDesdeRequest(InscripcionRequest request, String token) {
        Map<String, Object> usuario = usuarioClient.obtenerUsuarioId(request.getUsuarioId(), token);
        if (usuario == null || usuario.isEmpty()) throw new RuntimeException("Usuario no encontrado");

        Map<String, Object> torneo = torneoClient.obtenerTorneoId(request.getTorneoId(), token);
        if (torneo == null || torneo.isEmpty()) throw new RuntimeException("Torneo no encontrado");

        Map<String, Object> juego = juegosClient.obtenerJuegosId(request.getJuegoId(), token);
        if(juego == null || juego.isEmpty()) throw new RuntimeException("Juego no encontrado");

        Inscripcion inscripcion2 = new Inscripcion();

        inscripcion2.setUsuarioId(request.getUsuarioId());
        inscripcion2.setTorneoId(request.getTorneoId());
        inscripcion2.setJuegoId(request.getJuegoId());
        inscripcion2.setRolEquipo(request.getRolEquipo());
        inscripcion2.setFechaInscripcion(request.getFechaInscripcion());
        inscripcion2.setCupoConfirmado(request.getCupoConfirmado());

        //Client de Usuario
        inscripcion2.setUsername(usuario.get("username").toString());
        inscripcion2.setRegion(usuario.get("region").toString());

        //Client de Torneo
        inscripcion2.setNombreTorneo(torneo.get("nombreTorneo").toString());
        inscripcion2.setEstado(torneo.get("estado").toString());

        //Client de Juego
        inscripcion2.setTitulo(juego.get("titulo").toString());
        inscripcion2.setVersionJuego(juego.get("versionJuego").toString());
        inscripcion2.setCategoria(juego.get("categoria").toString());
        inscripcion2.setDesarrollador(juego.get("desarrollador").toString());

        if (juego.get("fechaInscripcion") != null) {
            try {
                String fechaStr = juego.get("fechaInscripcion").toString();
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                java.time.LocalDate localDate = java.time.LocalDate.parse(fechaStr, formatter);
                java.time.LocalDateTime fechaConvertida = localDate.atStartOfDay();
                inscripcion2.setFechaInscripcion(fechaConvertida);
            } catch (Exception e) {
                System.out.println("Error al convertir fecha: " + e.getMessage());
            }
        }


        return inscripcionRepository.save(inscripcion2);
    }

    public boolean eliminar(Integer id) {
        Inscripcion inscripcion = buscarPorId(id);
        if(inscripcion == null){
            return false;
        }
        inscripcionRepository.deleteById(id);
        return true;
    }


}
