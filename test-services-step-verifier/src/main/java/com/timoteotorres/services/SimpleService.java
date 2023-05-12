package com.timoteotorres.services;

import java.time.Duration;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SimpleService {

	public Mono<String> findOne(){
		return Mono.just("Hola");
	}
	
	public Flux<String> findAll(){
		return Flux.just("Hola", "Como", "Estas");
	}
	
	public Flux<String> findAllSlow(){
		return Flux.just("Hola", "Como", "Estas").delaySequence(Duration.ofSeconds(10));
	}
	
}
