package com.fullstack.notificacion_usuario.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NotificacionUsuarioConfig {

    @Bean
    @LoadBalanced // Enlaza el Spring Cloud LoadBalancer al constructor del WebClient
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
