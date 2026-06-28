package com.fullstack.usuario.config; // <-- Paquete correcto

import com.fullstack.usuario.model.Usuario; // <-- Import de tu Usuario actual
import com.fullstack.usuario.repository.UsuarioRepository; // <-- Import de tu repositorio
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(UsuarioRepository repository) {
        return args -> {
            // Imprimimos un mensaje en la consola para saber que Spring SI leyó esta clase
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                // 1. Crear el usuario manual inicial
                repository.save(Usuario.builder()
                        .username("admin_ana")
                        .email("ana.admin@example.com")
                        .region("LAT") // Usamos una región válida según tu validación de patrones
                        .puntaje(1500)
                        .build());

                // 2. Generar usuarios con Faker
                Faker faker = new Faker();
                for (int i = 0; i < 4; i++) {
                    repository.save(Usuario.builder()
                            .username(faker.name().username())
                            .email(faker.internet().emailAddress())
                            .region("LAT") // "LAT" para cumplir con tu @Pattern(regexp = "(?i)EU|LAT|NORT|AS")
                            .puntaje(faker.number().numberBetween(10, 1000))
                            .build());
                }
                System.out.println("=> ¡Usuarios insertados con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen usuarios en la base de datos, saltando Faker.");
            }
        };
    }
}