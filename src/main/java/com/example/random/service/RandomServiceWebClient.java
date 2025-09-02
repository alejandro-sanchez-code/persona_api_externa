package com.example.random.service;

import com.example.random.dto.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RandomServiceWebClient {

    @Autowired
    private WebClient webClient;

    public Mono<Random> obtenerReac() {
        return webClient.get()
                    .uri("https://randomuser.me/api")
                .retrieve()
                .bodyToMono(Random.class);
    }

}
