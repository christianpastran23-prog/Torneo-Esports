package com.fullstack.torneo_patrocinador.webClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class TorneoClient {

    @LoadBalanced
    private final WebClient webClient;

    public TorneoClient(@Value("${torneo-service.url}") String torneoServidor){
        this.webClient = WebClient.builder().baseUrl(torneoServidor).build();
    }

    public Map<String, Object> obtenerTorneoId(Integer id, String token){
        return this.webClient.get()
                .uri("/{id}", id)
                .header("Authorization", token)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Torneo no encontrado")))
                .bodyToMono(Map.class)
                .block();
    }
}
