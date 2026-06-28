package com.fullstack.comentario_torneo.config;

import com.fullstack.comentario_torneo.model.ComentarioTorneo;
import com.fullstack.comentario_torneo.repository.ComentarioTorneoRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Locale;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(ComentarioTorneoRepository repository) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER (COMENTARIOS)...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                // Instanciamos Faker en español para que los comentarios y regiones suenen locales
                Faker faker = new Faker(new Locale("es"));

                // Listas auxiliares simples para dar variedad lógica a los datos
                List<String> regiones = List.of("LAT", "EU", "NORT", "AS");
                List<String> juegosYTorneos = List.of("Torneo Nacional Valorant", "Copa Master LoL", "Circuito Smash Macul", "Liga Counter-Strike");

                // 1. Crear un comentario manual inicial (Admin o Prueba fija)
                ComentarioTorneo manual = new ComentarioTorneo();
                manual.setUsuarioId(1);
                manual.setTorneoId(100);
                manual.setUsername("pro_player99");
                manual.setRegion("LAT");
                manual.setMensajeComentario("¿A qué hora empieza la transmisión oficial del torneo?");
                manual.setNombreTorneo("Copa Master LoL");
                repository.save(manual);

                // 2. Generar 5 comentarios aleatorios con Faker
                for (int i = 0; i < 5; i++) {
                    ComentarioTorneo comentarioFake = new ComentarioTorneo();

                    // IDs ficticios de padres (simulando que apuntan a Usuario y Torneo)
                    comentarioFake.setUsuarioId(faker.number().numberBetween(2, 50));
                    comentarioFake.setTorneoId(faker.number().numberBetween(101, 120));

                    // Datos de texto usando las herramientas de Faker
                    comentarioFake.setUsername(faker.name().username());
                    comentarioFake.setRegion(faker.options().nextElement(regiones));

                    // SOLUCIÓN: Usamos una frase de 10 palabras que asegura no romper el límite de VARCHAR(255)
                    comentarioFake.setMensajeComentario(faker.lorem().sentence(10));

                    comentarioFake.setNombreTorneo(faker.options().nextElement(juegosYTorneos));

                    repository.save(comentarioFake);
                }

                System.out.println("=> ¡Comentarios insertados con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen comentarios en la base de datos, saltando Faker.");
            }
        };
    }
}