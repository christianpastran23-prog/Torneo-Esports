package com.fullstack.torneo.service;

import com.fullstack.torneo.dto.TorneoRequest;
import com.fullstack.torneo.model.Torneo;
import com.fullstack.torneo.repository.TorneoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    public List<Torneo> listarTodos(){return torneoRepository.findAll();}

    public Torneo buscarPorId(Integer id){return torneoRepository.findById(id).orElse(null);}

    public Torneo guardar(Torneo torneo){return torneoRepository.save(torneo);}

    public Torneo crearDesdeRequest(TorneoRequest request){
        Torneo torneo = new Torneo();
        torneo.setNombreTorneo(request.getNombreTorneo());
        torneo.setFechaInicio(request.getFechaInicio());
        torneo.setEstado(request.getEstado());
        torneo.setPremioDolares(request.getPremioDolares());

        return guardar(torneo);
    }

    public Torneo actualizar(Integer id, TorneoRequest request){
        Torneo torneo = buscarPorId(id);
        if (torneo == null){
            return null;
        }
        torneo.setNombreTorneo(request.getNombreTorneo());
        torneo.setFechaInicio(request.getFechaInicio());
        torneo.setEstado(request.getEstado());
        torneo.setPremioDolares(request.getPremioDolares());
        return guardar(torneo);
    }

public boolean eliminar(Integer id){
        Torneo torneo = buscarPorId(id);
        if (torneo == null){
            return false;
        }
        torneoRepository.deleteById(id);
        return true;
    }
}
