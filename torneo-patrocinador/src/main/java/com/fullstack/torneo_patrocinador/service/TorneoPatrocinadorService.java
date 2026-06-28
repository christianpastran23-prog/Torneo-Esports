package com.fullstack.torneo_patrocinador.service;


import com.fullstack.torneo_patrocinador.webClient.TorneoClient;
import com.fullstack.torneo_patrocinador.dto.TorneoPatrocinadorRequest;
import com.fullstack.torneo_patrocinador.model.TorneoPatrocinador;
import com.fullstack.torneo_patrocinador.repository.TorneoPatrocinadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional

public class TorneoPatrocinadorService {

    @Autowired
    private TorneoPatrocinadorRepository torneoPatrocinadorRepository;

    @Autowired
    private TorneoClient torneoClient;

    public List<TorneoPatrocinador> getTorneoPatrocinador() {return torneoPatrocinadorRepository.findAll();}

    public TorneoPatrocinador buscarPorId(Integer id){return torneoPatrocinadorRepository.findById(id).orElse(null);}

    public TorneoPatrocinador guardarTorneoPatrocinador(TorneoPatrocinador patrocinador) {return torneoPatrocinadorRepository.save(patrocinador);}

    public TorneoPatrocinador crearDesdeRequest(TorneoPatrocinadorRequest request, String token) {

        Map<String, Object> torneo = torneoClient.obtenerTorneoId(request.getTorneoId(), token);
        if (torneo == null || torneo.isEmpty()) throw new RuntimeException("Torneo no encontrado");

        TorneoPatrocinador torneoPatrocinador = new TorneoPatrocinador();
        torneoPatrocinador.setTorneoId(request.getTorneoId());
        torneoPatrocinador.setNombreEmpresa(request.getNombreEmpresa());
        torneoPatrocinador.setContacto(request.getContacto());
        torneoPatrocinador.setNombreTorneo(torneo.get("nombreTorneo").toString());

        return torneoPatrocinador;
    }

    public boolean eliminar(Integer id) {
        TorneoPatrocinador torneoPatrocinador = buscarPorId(id);
        if(torneoPatrocinador == null){
            return false;
        }
        torneoPatrocinadorRepository.deleteById(id);
        return true;
    }


}
