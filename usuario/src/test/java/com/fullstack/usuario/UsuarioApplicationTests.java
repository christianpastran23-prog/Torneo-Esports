package com.fullstack.usuario;

import com.fullstack.usuario.model.Usuario;
import com.fullstack.usuario.repository.UsuarioRepository;
import com.fullstack.usuario.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

	@Mock
	private UsuarioRepository repository;

	@InjectMocks
	private UsuarioService service;

	@Test
	void buscarPorId_debeRetornarRegistroCuandoExiste() {

		Usuario entidad = new Usuario();
		entidad.setId(1L);
		entidad.setUsername("test_user");
		entidad.setEmail("test@example.com");


		when(repository.findById(1L)).thenReturn(Optional.of(entidad));

		Usuario resultado = service.buscarPorId(1L);


		assertNotNull(resultado);
		assertEquals(1L, resultado.getId());
		assertEquals("test_user", resultado.getUsername());
		verify(repository).findById(1L);
	}

	@Test
	void buscarPorId_debeRetornarNullCuandoNoExiste() {

		when(repository.findById(99L)).thenReturn(Optional.empty());


		Usuario resultado = service.buscarPorId(99L);


		assertNull(resultado);


		verify(repository).findById(99L);
	}
}