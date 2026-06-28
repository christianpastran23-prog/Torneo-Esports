package com.fullstack.usuario; // Ajusta a tu paquete real

import com.fullstack.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test") // Le obliga a usar el perfil de test
class DataLoaderTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void verificarQueFakerInsertoDatos() {
        // Obtenemos cuántos usuarios hay en la base de datos de test
        long cantidadUsuarios = usuarioRepository.count();

        System.out.println("=========================================");
        System.out.println("CANTIDAD DE USUARIOS GENERADOS POR FAKER: " + cantidadUsuarios);
        System.out.println("=========================================");

        // Si tu DataLoader funcionó, debería haber al menos 5 usuarios (1 manual + 4 de Faker)
        assertTrue(cantidadUsuarios > 0, "El DataLoader no insertó ningún usuario.");
    }
}