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
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                repository.save(Usuario.builder()
                        .username("admin_ana")
                        .email("ana.admin@example.com")
                        .region("LAT")
                        .puntaje(1500)
                        .build());

                Faker faker = new Faker();
                for (long i = 0; i < 4; i++) {
                    repository.save(Usuario.builder()
                            .username(faker.name().username())
                            .email(faker.internet().emailAddress())
                            .region("LAT")
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