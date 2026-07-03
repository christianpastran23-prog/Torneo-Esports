package com.fullstack.favorito_juego.service;

import com.fullstack.favorito_juego.dto.FavoritoJuegoRequest;
import com.fullstack.favorito_juego.model.FavoritoJuego;
import com.fullstack.favorito_juego.repository.FavoritoJuegoRepository;
import com.fullstack.favorito_juego.webClient.JuegosClient;
import com.fullstack.favorito_juego.webClient.UsuarioClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional

public class FavoritoJuegoService {

    @Autowired
    private FavoritoJuegoRepository favoritoJuegoRepository;

    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private JuegosClient juegosClient;

    public List<FavoritoJuego> getFavoritoJuego() {return favoritoJuegoRepository.findAll();}

    public FavoritoJuego buscarPorId(Integer id){return favoritoJuegoRepository.findById(id).orElse(null);}

    public FavoritoJuego guardarFavoritoJuego(FavoritoJuego favorito) {return favoritoJuegoRepository.save(favorito);}

    public FavoritoJuego crearDesdeRequest(FavoritoJuegoRequest request, String token) {
        Map<String, Object> usuario = usuarioClient.obtenerUsuarioId(request.getUsuarioId(), token);
        if (usuario == null || usuario.isEmpty()) throw new RuntimeException("Usuario no encontrado");

        Map<String, Object> juego = juegosClient.obtenerJuegosId(request.getJuegoId(), token);
        if(juego == null || juego.isEmpty()) throw new RuntimeException("Juego no encontrado");

        FavoritoJuego favorito = new FavoritoJuego();

        favorito.setUsuarioId(request.getUsuarioId());
        favorito.setJuegoId(request.getJuegoId());
        favorito.setHorasDeJuego(request.getHorasDeJuego());
        favorito.setUsername(usuario.get("username").toString());
        favorito.setRegion(usuario.get("region").toString());
        favorito.setTitulo(juego.get("titulo").toString());
        favorito.setVersionJuego(juego.get("versionJuego").toString());
        favorito.setDesarrollador(juego.get("desarrollador").toString());



        return favoritoJuegoRepository.save(favorito);
    }

    public boolean eliminar(Integer id) {
        FavoritoJuego favoritoJuego = buscarPorId(id);
        if(favoritoJuego == null){
            return false;
        }
        favoritoJuegoRepository.deleteById(id);
        return true;
    }


}
