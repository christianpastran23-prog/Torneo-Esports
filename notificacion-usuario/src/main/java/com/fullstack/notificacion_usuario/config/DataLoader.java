package com.fullstack.notificacion_usuario.config;

import com.fullstack.notificacion_usuario.model.NotificacionUsuario;
import com.fullstack.notificacion_usuario.repository.NotificacionUsuarioRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Locale;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(NotificacionUsuarioRepository repository) {
        return args -> {
            System.out.println("=========================================");
            System.out.println("EJECUTANDO DATALOADER CON FAKER (NOTIFICACIONES)...");
            System.out.println("=========================================");

            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));

                // Opciones para el estado de lectura (manejado como String en tu entidad)
                List<String> estadosLeido = List.of("LEIDO", "NO_LEIDO");

                // Lista de mensajes típicos de una plataforma de torneos para darle realismo
                List<String> mensajesFicticios = List.of(
                        "Tu inscripción al torneo ha sido aprobada con éxito.",
                        "Un administrador ha modificado la fecha de inicio de tu partida.",
                        "¡Felicidades! Has recibido un nuevo premio en dólares en tu perfil.",
                        "Alguien comentó en el muro del torneo en el que participas.",
                        "Tu reporte de emparejamiento ha sido revisado por soporte."
                );

                // 1. Registro manual inicial
                NotificacionUsuario manual = new NotificacionUsuario();
                manual.setUsuarioId(1);
                manual.setUsername("admin_ana");
                manual.setMensaje("Bienvenido a la plataforma de torneos. Completa tu perfil para empezar.");
                manual.setLeido("NO_LEIDO");
                repository.save(manual);

                // 2. Generar 5 registros aleatorios con Faker
                for (int i = 0; i < 5; i++) {
                    NotificacionUsuario fakeNotificacion = new NotificacionUsuario();

                    // ID ficticio del servicio padre (Usuario)
                    fakeNotificacion.setUsuarioId(faker.number().numberBetween(2, 50));

                    // Datos generados con Faker
                    fakeNotificacion.setUsername(faker.name().username());
                    fakeNotificacion.setMensaje(faker.options().nextElement(mensajesFicticios));
                    fakeNotificacion.setLeido(faker.options().nextElement(estadosLeido));

                    repository.save(fakeNotificacion);
                }

                System.out.println("=> ¡Notificaciones insertadas con éxito por Faker!");
            } else {
                System.out.println("=> Ya existen notificaciones en la base de datos, saltando Faker.");
            }
        };
    }
}