package com.fullstack.ficha.config;

import com.fullstack.ficha.model.FichaPartida;
import com.fullstack.ficha.repository.FichaPartidaRepository;
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
    CommandLineRunner cargarDatos(FichaPartidaRepository repository) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER (FICHA PARTIDA)...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));

                List<String> regiones = List.of("LAT", "EU", "NORT", "AS");
                List<String> estados = List.of("EN_PROGRESO", "FINALIZADO", "CANCELADO");
                List<String> resultados = List.of("VICTORIA", "DERROTA", "EMPATE");
                List<String> categorias = List.of("Shooter", "MOBA", "Estrategia", "Battle Royale");

                FichaPartida manual = new FichaPartida();
                manual.setUsuarioId(1);
                manual.setTorneoId(10);
                manual.setJuegoId(5);
                manual.setUsername("pro_player99");
                manual.setEmail("player99@example.com");
                manual.setPuntaje(1200);
                manual.setNombreTorneo("Copa Master LoL");
                manual.setEstado("FINALIZADO");
                manual.setFechaInicio(LocalDateTime.now().minusDays(2));
                manual.setRegion("LAT");
                manual.setPremioDolares(500);
                manual.setTitulo("League of Legends");
                manual.setCategoria("MOBA");
                manual.setKD(3); // K/D Ratio simplificado como entero
                manual.setResultado("VICTORIA");
                manual.setPuntos(150);
                manual.setFechaPartida(LocalDateTime.now().minusDays(1));
                repository.save(manual);

                for (int i = 0; i < 5; i++) {
                    FichaPartida fakeFicha = new FichaPartida();

                    fakeFicha.setUsuarioId(faker.number().numberBetween(2, 50));
                    fakeFicha.setTorneoId(faker.number().numberBetween(11, 30));
                    fakeFicha.setJuegoId(faker.number().numberBetween(6, 20));

                    fakeFicha.setUsername(faker.name().username());
                    fakeFicha.setEmail(faker.internet().emailAddress());
                    fakeFicha.setPuntaje(faker.number().numberBetween(100, 3000));
                    fakeFicha.setRegion(faker.options().nextElement(regiones));

                    fakeFicha.setNombreTorneo("Torneo " + faker.videoGame().title());
                    fakeFicha.setEstado(faker.options().nextElement(estados));
                    fakeFicha.setFechaInicio(LocalDateTime.now().minusDays(faker.number().numberBetween(3, 10)));
                    fakeFicha.setPremioDolares(faker.number().numberBetween(50, 2000));

                    fakeFicha.setTitulo(faker.videoGame().title());
                    fakeFicha.setCategoria(faker.options().nextElement(categorias));

                    fakeFicha.setKD(faker.number().numberBetween(0, 10)); // Representación simple de bajas/muertes
                    fakeFicha.setResultado(faker.options().nextElement(resultados));
                    fakeFicha.setPuntos(faker.number().numberBetween(0, 500));
                    fakeFicha.setFechaPartida(LocalDateTime.now().minusHours(faker.number().numberBetween(1, 48)));

                    repository.save(fakeFicha);
                }

                System.out.println("=> ¡Fichas de partidas insertadas con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen fichas de partidas en la base de datos, saltando Faker.");
            }
        };
    }
}