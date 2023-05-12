package com.timoteotorres.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

	public Mono<ServerResponse> showMessageMono(ServerRequest serverRequest) {
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_PLAIN)
				.body(Mono.just("Welcome to my channel"), String.class);
	}
	
	public Mono<ServerResponse> showMessageFlux(ServerRequest serverRequest) {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(Flux.just("Welcome", "to my channel")
						.delayElements(Duration.ofSeconds(1))
						.log()
						, String.class);
	}
	
}
