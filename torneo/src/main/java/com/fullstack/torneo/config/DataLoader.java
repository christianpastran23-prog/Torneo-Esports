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

                List<String> estadosTorneo = List.of("INSCRIPCIONES_ABIERTAS", "EN_CURSO", "FINALIZADO");

                Torneo manual = new Torneo();
                manual.setNombreTorneo("Copa Master LoL");
                manual.setFechaInicio(LocalDateTime.now().plusDays(5));
                manual.setEstado("INSCRIPCIONES_ABIERTAS");
                manual.setPremioDolares(1500);
                repository.save(manual);

                for (int i = 0; i < 5; i++) {
                    Torneo fakeTorneo = new Torneo();

                    fakeTorneo.setNombreTorneo("Torneo Oficial " + faker.videoGame().title());

                    fakeTorneo.setFechaInicio(LocalDateTime.now().plusDays(faker.number().numberBetween(2, 15)));

                    fakeTorneo.setEstado(faker.options().nextElement(estadosTorneo));

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