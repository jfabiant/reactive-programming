package com.timoteotorres.introduction;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example01 {

	public static void main(String[] args) {
		
		List<Integer> elementsFromMono = new ArrayList<>();
		List<Integer> elementsFromFlux = new ArrayList<>();
		
		Mono<Integer> mono = Mono.just(12);
		
		Flux<Integer> flux = Flux.just(12, 13, 14, 15, 4, 5, 6);
		
		mono.subscribe(elementsFromMono::add);
		
		flux.subscribe(elementsFromFlux::add);
		
		System.out.println(elementsFromMono);
		System.out.println(elementsFromFlux);
		
	}

}
