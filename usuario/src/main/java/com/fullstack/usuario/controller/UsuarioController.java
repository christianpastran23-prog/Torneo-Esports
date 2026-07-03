package com.fullstack.usuario.controller;

import com.fullstack.usuario.assembler.UsuarioModelAssembler;
import com.fullstack.usuario.dto.UsuarioRequest;
import com.fullstack.usuario.model.Usuario;
import com.fullstack.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista hipermedia de todos los usuarios")
    public CollectionModel<EntityModel<Usuario>> listar() {
        List<EntityModel<Usuario>> usuarios = usuarioService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).listar()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener el usuario por id", description = "Busca el usuario por su id y añade sus enlaces HATEOAS")
    public ResponseEntity<EntityModel<Usuario>> buscarPorId(@PathVariable("id") Long id) { // <--- CAMBIADO A Long
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(usuario));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear un usuario", description = "Realiza la creacion de un usuario y retorna su URI de acceso")
    public ResponseEntity<EntityModel<Usuario>> guardar(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuarioGuardado = usuarioService.crearDesdeRequest(request);

        return ResponseEntity
                .created(linkTo(methodOn(UsuarioController.class).buscarPorId(usuarioGuardado.getId())).toUri())
                .body(assembler.toModel(usuarioGuardado));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un usuario", description = "Busca un usuario por su id, actualiza sus datos y refresca sus enlaces")
    public ResponseEntity<EntityModel<Usuario>> actualizar(@PathVariable("id") Long id, @Valid @RequestBody UsuarioRequest request) { // <--- CAMBIADO A Long
        Usuario usuarioActualizado = usuarioService.actualizar(id, request);
        if (usuarioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(usuarioActualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Borrar usuario por su id", description = "Busca un usuario por su id y lo elimina de la base de datos")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) { // <--- CAMBIADO A Long
        boolean eliminado = usuarioService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/publico")
    @Operation(summary = "API publica")
    public ResponseEntity<String> publico() {
        return ResponseEntity.ok("Endpoint público - Torneo Esports");
    }
}