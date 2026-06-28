package com.fullstack.torneo_patrocinador.config;

import com.fullstack.torneo_patrocinador.model.TorneoPatrocinador;
import com.fullstack.torneo_patrocinador.repository.TorneoPatrocinadorRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(TorneoPatrocinadorRepository repository) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER (PATROCINADORES)...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));

                // 1. Registro manual inicial
                TorneoPatrocinador manual = new TorneoPatrocinador();
                manual.setTorneoId(1);
                manual.setNombreEmpresa("Logitech G");
                manual.setContacto("contacto@logitechg.com");
                manual.setNombreTorneo("Copa Master LoL");
                repository.save(manual);

                // 2. Generar 5 registros aleatorios con Faker
                for (int i = 0; i < 5; i++) {
                    TorneoPatrocinador fakePatrocinador = new TorneoPatrocinador();

                    // ID ficticio que apunta conceptualmente a tu servicio padre (Torneo)
                    fakePatrocinador.setTorneoId(faker.number().numberBetween(2, 30));

                    // Datos strings generados con Faker de marcas, emails y juegos
                    fakePatrocinador.setNombreEmpresa(faker.company().name());
                    fakePatrocinador.setContacto(faker.internet().emailAddress());
                    fakePatrocinador.setNombreTorneo("Torneo Oficial " + faker.videoGame().title());

                    repository.save(fakePatrocinador);
                }

                System.out.println("=> ¡Patrocinadores insertados con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen patrocinadores en la base de datos, saltando Faker.");
            }
        };
    }
}