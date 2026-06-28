package com.fullstack.torneo.config;

import com.fullstack.torneo.model.Torneo;
import com.fullstack.torneo.repository.TorneoRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(TorneoRepository repository) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER (TORNEOS)...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));

                // Lista de estados posibles para un torneo competitivo
                List<String> estadosTorneo = List.of("INSCRIPCIONES_ABIERTAS", "EN_CURSO", "FINALIZADO");

                // 1. Registro manual inicial de prueba fija
                Torneo manual = new Torneo();
                manual.setNombreTorneo("Copa Master LoL");
                manual.setFechaInicio(LocalDateTime.now().plusDays(5));
                manual.setEstado("INSCRIPCIONES_ABIERTAS");
                manual.setPremioDolares(1500);
                repository.save(manual);

                // 2. Generar 5 registros aleatorios con Faker
                for (int i = 0; i < 5; i++) {
                    Torneo fakeTorneo = new Torneo();

                    // Generamos nombres competitivos usando la categoría de videojuegos de Faker
                    fakeTorneo.setNombreTorneo("Torneo Oficial " + faker.videoGame().title());

                    // Fechas aleatorias distribuidas entre los próximos 2 a 15 días
                    fakeTorneo.setFechaInicio(LocalDateTime.now().plusDays(faker.number().numberBetween(2, 15)));

                    fakeTorneo.setEstado(faker.options().nextElement(estadosTorneo));

                    // Premios que escalan de 100 en 100 dólares hasta los $5000
                    fakeTorneo.setPremioDolares(faker.number().numberBetween(1, 50) * 100);

                    repository.save(fakeTorneo);
                }

                System.out.println("=> ¡Torneos insertados con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen torneos en la base de datos, saltando Faker.");
            }
        };
    }
}