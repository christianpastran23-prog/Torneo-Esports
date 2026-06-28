package com.fullstack.juegos.config;

import com.fullstack.juegos.model.Juego;
import com.fullstack.juegos.repository.JuegosRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Locale;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(JuegosRepository repository) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER (JUEGOS)...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));

                // Lista de categorías comunes en eSports y gaming competitivo
                List<String> categorias = List.of("Shooter", "MOBA", "Estrategia", "Battle Royale", "Lucha", "Deportes");

                // 1. Registro manual inicial
                Juego manual = new Juego();
                manual.setTitulo("Minecraft");
                manual.setDesarrollador("Mojang Studios");
                manual.setVersionJuego("v1.21");
                manual.setCategoria("Estrategia");
                repository.save(manual);

                // 2. Generar 5 registros aleatorios con Faker
                for (int i = 0; i < 5; i++) {
                    Juego fakeJuego = new Juego();

                    // Atributos generados con la librería Faker
                    fakeJuego.setTitulo(faker.videoGame().title());
                    fakeJuego.setDesarrollador(faker.app().author()); // Simula una empresa/autor de software
                    fakeJuego.setVersionJuego("v" + faker.app().version());
                    fakeJuego.setCategoria(faker.options().nextElement(categorias));

                    repository.save(fakeJuego);
                }

                System.out.println("=> ¡Juego insertados con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen juegos en la base de datos, saltando Faker.");
            }
        };
    }
}