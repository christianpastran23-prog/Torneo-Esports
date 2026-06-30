package com.fullstack.usuario.assembler;

import com.fullstack.usuario.controller.UsuarioController;
import com.fullstack.usuario.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        // Creamos el modelo y le añadimos sus enlaces automáticos
        return EntityModel.of(usuario,
                // Enlace al detalle del usuario individual (self)
                linkTo(methodOn(UsuarioController.class).buscarPorId(Math.toIntExact(usuario.getId()))).withSelfRel(),

                // Enlace a la lista completa de usuarios
                linkTo(methodOn(UsuarioController.class).listar()).withRel("usuarios"),

                // Enlace opcional para eliminar este usuario específico
                linkTo(methodOn(UsuarioController.class).eliminar(Math.toIntExact(usuario.getId()))).withRel("eliminar")
        );
    }
}