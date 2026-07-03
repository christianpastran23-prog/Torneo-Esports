package com.fullstack.usuario;

import com.fullstack.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class DataLoaderTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void verificarQueFakerInsertoDatos() {

        long cantidadUsuarios = usuarioRepository.count();

        System.out.println("=========================================");
        System.out.println("CANTIDAD DE USUARIOS GENERADOS POR FAKER: " + cantidadUsuarios);
        System.out.println("=========================================");

        assertTrue(cantidadUsuarios > 0, "El DataLoader no insertó ningún usuario.");
    }
}