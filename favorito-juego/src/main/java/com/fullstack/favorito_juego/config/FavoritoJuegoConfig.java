package com.fullstack.favorito_juego.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FavoritoJuegoConfig {

    @Bean
    @LoadBalanced // Enlaza el Spring Cloud LoadBalancer al constructor del WebClient
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}