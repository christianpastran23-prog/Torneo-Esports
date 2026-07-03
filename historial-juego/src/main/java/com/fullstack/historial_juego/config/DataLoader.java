package com.fullstack.historial_juego.config;

import com.fullstack.historial_juego.model.HistorialJuego;
import com.fullstack.historial_juego.repository.HistorialJuegosRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(HistorialJuegosRepository repository) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER (HISTORIAL)...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));

                HistorialJuego manual = new HistorialJuego();
                manual.setUsuarioId(1);
                manual.setJuegoId(5);
                manual.setUsername("admin_ana");
                manual.setTitulo("Valorant");
                manual.setTiempoJugado(450); // Tiempo en minutos
                repository.save(manual);

                for (int i = 0; i < 5; i++) {
                    HistorialJuego fakeHistorial = new HistorialJuego();

                    // IDs ficticios que simulan apuntar a tus servicios padres (Usuario y Juego)
                    fakeHistorial.setUsuarioId(faker.number().numberBetween(2, 50));
                    fakeHistorial.setJuegoId(faker.number().numberBetween(6, 25));

                    // Datos strings e int usando Faker
                    fakeHistorial.setUsername(faker.name().username());
                    fakeHistorial.setTitulo(faker.videoGame().title());
                    fakeHistorial.setTiempoJugado(faker.number().numberBetween(10, 5000)); // Minutos acumulados

                    repository.save(fakeHistorial);
                }

                System.out.println("=> ¡Historiales de juego insertados con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen registros en el historial, saltando Faker.");
            }
        };
    }
}