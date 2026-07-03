package com.fullstack.historial_juego.webClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class JuegosClient {
    private final WebClient webClient;

    public JuegosClient(@Value("${juegos-service.url}") String juegosServidor){
        this.webClient = WebClient.builder().baseUrl(juegosServidor).build();
    }

    public Map<String, Object> obtenerJuegosId(Integer id, String token){
        return this.webClient.get()
                .uri("/{id}", id)
                .header("Authorization", token)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Juego no encontrado")))
                .bodyToMono(Map.class)
                .block();
    }
}