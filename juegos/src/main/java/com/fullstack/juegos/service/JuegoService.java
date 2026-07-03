package com.fullstack.juegos.service;

import com.fullstack.juegos.dto.JuegosRequest;
import com.fullstack.juegos.model.Juego;
import com.fullstack.juegos.repository.JuegosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class JuegoService {

    @Autowired
    private JuegosRepository juegosRepository;

    public List<Juego> listarTodos(){return juegosRepository.findAll();}

    public Juego buscarPorId(Integer id){return juegosRepository.findById(id).orElse(null);}

    public Juego guardar(Juego juego){return juegosRepository.save(juego);}

    public Juego crearDesdeRequest(JuegosRequest request){
        Juego juego = new Juego();
        juego.setTitulo(request.getTitulo());
        juego.setDesarrollador(request.getDesarrollador());
        juego.setVersionJuego(request.getVersionJuego());
        juego.setCategoria(request.getCategoria());

        return guardar(juego);
    }

    public Juego actualizar(Integer id, JuegosRequest request){
        Juego juego = buscarPorId(id);
        if (juego == null){
            return null;
        }
        juego.setTitulo(request.getTitulo());
        juego.setDesarrollador(request.getDesarrollador());
        juego.setVersionJuego(request.getVersionJuego());
        juego.setCategoria(request.getCategoria());
        return guardar(juego);
    }

public boolean eliminar(Integer id){
        Juego juego = buscarPorId(id);
        if (juego == null){
            return false;
        }
        juegosRepository.deleteById(id);
        return true;
    }
}
