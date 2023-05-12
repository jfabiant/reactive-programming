package com.timoteotorres;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.timoteotorres.services.SimpleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class ServiceStepVerifierApplicationTest {

	@Autowired
	SimpleService simpleService;
	
	@Test
	void testMono() {
		Mono<String> one = this.simpleService.findOne();
		StepVerifier.create(one).expectNext("Hola").verifyComplete();
	}
	
	@Test
	void testFlux() {
		Flux<String> many = this.simpleService.findAll();
		StepVerifier.create(many).expectNext("Hola", "Como", "Estas").verifyComplete();
	}
	
	@Test
	void testFluxSlow() {
		Flux<String> many = this.simpleService.findAllSlow();
		StepVerifier.create(many)
		.expectNext("Hola")
		.thenAwait(Duration.ofSeconds(1))
		.expectNext("Como")
		.thenAwait(Duration.ofSeconds(1))
		.expectNext("Estas")
		.thenAwait(Duration.ofSeconds(1))
		.verifyComplete();
		
	}
	
}
