package com.fullstack.favorito_juego.config;

import com.fullstack.favorito_juego.model.FavoritoJuego;
import com.fullstack.favorito_juego.repository.FavoritoJuegoRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Locale;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(FavoritoJuegoRepository repository) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER (FAVORITOS)...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));

                List<String> regiones = List.of("LAT", "EU", "NORT", "AS");

                // 1. Registro manual inicial de prueba fija
                FavoritoJuego manual = new FavoritoJuego();
                manual.setUsuarioId(1);
                manual.setJuegoId(10);
                manual.setUsername("admin_ana");
                manual.setRegion("LAT");
                manual.setTitulo("Counter-Strike 2");
                manual.setVersionJuego("v2.1.4");
                manual.setDesarrollador("Valve");
                manual.setHorasDeJuego(350);
                repository.save(manual);

                // 2. Generar 5 registros aleatorios con Faker
                for (int i = 0; i < 5; i++) {
                    FavoritoJuego favFake = new FavoritoJuego();

                    // IDs simulados que apuntan a tus servicios padre
                    favFake.setUsuarioId(faker.number().numberBetween(2, 50));
                    favFake.setJuegoId(faker.number().numberBetween(11, 30));

                    // Datos del Jugador
                    favFake.setUsername(faker.name().username());
                    favFake.setRegion(faker.options().nextElement(regiones));

                    // Datos del Videojuego usando Faker
                    favFake.setTitulo(faker.videoGame().title());
                    favFake.setVersionJuego("v" + faker.app().version());
                    favFake.setDesarrollador(faker.app().author()); // Nombre simulado de empresa/creador
                    favFake.setHorasDeJuego(faker.number().numberBetween(5, 1200));

                    repository.save(favFake);
                }

                System.out.println("=> ¡Juegos favoritos insertados con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen juegos favoritos en la base de datos, saltando Faker.");
            }
        };
    }
}