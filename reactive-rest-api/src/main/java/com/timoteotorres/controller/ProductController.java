package com.timoteotorres.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello")
public class ProductController {

	@GetMapping("/mono")
	public Mono<String> getMono(){
		return Mono.just("Hi getMono()");
	}
	
	@GetMapping(path="/flux",produces=MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<String> getFlux(){
		Flux<String> message = Flux.just("Hello getFlux()", "How are you?")
				.delayElements(Duration.ofSeconds(1))
				.log();
		return message;
	}
	
}
