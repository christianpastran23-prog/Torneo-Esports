package com.fullstack.usuario.service;

import com.fullstack.usuario.dto.UsuarioRequest;
import com.fullstack.usuario.model.Usuario;
import com.fullstack.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos(){return usuarioRepository.findAll();}

    public Usuario buscarPorId(Integer id){return usuarioRepository.findById(id).orElse(null);}

    public Usuario guardar(Usuario usuario){return usuarioRepository.save(usuario);}

    public Usuario crearDesdeRequest(UsuarioRequest request){
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setRegion(request.getRegion());
        usuario.setEmail(request.getEmail());
        usuario.setPuntaje(request.getPuntaje());

        return guardar(usuario);
    }

    public Usuario actualizar(Integer id, UsuarioRequest request){
        Usuario usuario = buscarPorId(id);
        if (usuario == null){
            return null;
        }
        usuario.setUsername(request.getUsername());
        usuario.setRegion(request.getRegion());
        usuario.setEmail(request.getEmail());
        usuario.setPuntaje(request.getPuntaje());


        return guardar(usuario);
    }

    public boolean eliminar(Integer id){
        Usuario usuario = buscarPorId(id);
        if(usuario == null){
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;
    }

}
