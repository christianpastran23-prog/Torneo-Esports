package com.fullstack.inscripcion.config;

import com.fullstack.inscripcion.model.Inscripcion;
import com.fullstack.inscripcion.repository.InscripcionRepository;
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
    CommandLineRunner cargarDatos(InscripcionRepository repository) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER (INSCRIPCION)...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));

                // Listas auxiliares específicas para inscripciones de torneos
                List<String> regiones = List.of("LAT", "EU", "NORT", "AS");
                List<String> estados = List.of("PENDIENTE", "ACEPTADA", "RECHAZADA");
                List<String> categorias = List.of("Shooter", "MOBA", "Estrategia", "Battle Royale");
                List<String> rolesEquipo = List.of("Capitán/IGL", "Líder", "Fragger", "Soporte", "Suplente", "Solitario");

                // 1. Registro manual inicial de prueba fija
                Inscripcion manual = new Inscripcion();
                manual.setUsuarioId(1);
                manual.setTorneoId(10);
                manual.setJuegoId(5);
                manual.setUsername("pro_player99");
                manual.setRegion("LAT");
                manual.setNombreTorneo("Copa Master LoL");
                manual.setEstado("ACEPTADA");
                manual.setTitulo("League of Legends");
                manual.setVersionJuego("v14.2");
                manual.setDesarrollador("Riot Games");
                manual.setCategoria("MOBA");
                manual.setRolEquipo("Capitán/IGL");
                manual.setFechaInscripcion(LocalDateTime.now().minusDays(3));
                manual.setCupoConfirmado(true);
                repository.save(manual);

                // 2. Generar 5 registros aleatorios con Faker
                for (int i = 0; i < 5; i++) {
                    Inscripcion fakeInscripcion = new Inscripcion();

                    // IDs ficticios vinculados conceptualmente a tus 3 microservicios padres
                    fakeInscripcion.setUsuarioId(faker.number().numberBetween(2, 50));
                    fakeInscripcion.setTorneoId(faker.number().numberBetween(11, 30));
                    fakeInscripcion.setJuegoId(faker.number().numberBetween(6, 20));

                    // Datos de Usuario
                    fakeInscripcion.setUsername(faker.name().username());
                    fakeInscripcion.setRegion(faker.options().nextElement(regiones));

                    // Datos de Torneo e Inscripción
                    fakeInscripcion.setNombreTorneo("Torneo " + faker.videoGame().title());
                    fakeInscripcion.setEstado(faker.options().nextElement(estados));
                    fakeInscripcion.setRolEquipo(faker.options().nextElement(rolesEquipo));
                    fakeInscripcion.setFechaInscripcion(LocalDateTime.now().minusHours(faker.number().numberBetween(1, 72)));
                    fakeInscripcion.setCupoConfirmado(faker.random().nextBoolean());

                    // Datos de Juego
                    fakeInscripcion.setTitulo(faker.videoGame().title());
                    fakeInscripcion.setVersionJuego("v" + faker.app().version());
                    fakeInscripcion.setDesarrollador(faker.app().author());
                    fakeInscripcion.setCategoria(faker.options().nextElement(categorias));

                    repository.save(fakeInscripcion);
                }

                System.out.println("=> ¡Inscripciones insertadas con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen inscripciones en la base de datos, saltando Faker.");
            }
        };
    }
}