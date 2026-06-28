package com.fullstack.usuario; // <-- Ajusta este paquete según tu proyecto

import com.fullstack.usuario.exception.ResourceNotFoundException; // <-- Ajusta tus excepciones
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
	private UsuarioService service; // El servicio que vas a testear

	@Test
	void buscarPorId_debeRetornarRegistroCuandoExiste() {
		// Arrange (Preparar los datos)
		Usuario entidad = new Usuario();
		entidad.setId(1); // Usamos Integer (1) en lugar de Long (1L)
		entidad.setUsername("test_user");
		entidad.setEmail("test@example.com");

		// Simulamos que cuando el repositorio busque el ID 1, devuelva nuestro usuario
		when(repository.findById(1)).thenReturn(Optional.of(entidad));

		// Act (Ejecutar la acción)
		Usuario resultado = service.buscarPorId(1);

		// Assert (Verificar que todo salió bien)
		assertNotNull(resultado);
		assertEquals(1, resultado.getId());
		assertEquals("test_user", resultado.getUsername());
		verify(repository).findById(1); // Verifica que el repositorio realmente fue consultado
	}

	@Test
	void buscarPorId_debeLanzarExcepcionCuandoNoExiste() {
		// Arrange: Simulamos que el repositorio no encuentra el ID 99
		when(repository.findById(99)).thenReturn(Optional.empty());

		// Act & Assert: Verificamos que al buscar el 99, el servicio lance la excepción esperada
		assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99));

		// Verifica que se haya llamado al repositorio
		verify(repository).findById(99);
	}
}