package com.example.random.service;

import com.example.random.dto.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class RandomServiceWebClienTest {

    private RandomService randomService;

    @BeforeEach
    void setUp() {
        WebClient webClient = WebClient.builder().build();
        randomService = new RandomService(webClient);
    }

    @Test
    void testObtener() {
        Mono<Random> resultado = randomService.obtener();

        StepVerifier.create(resultado)
                .expectNextMatches(random -> random != null && random.getResults() != null)
                .verifyComplete();
    }
}