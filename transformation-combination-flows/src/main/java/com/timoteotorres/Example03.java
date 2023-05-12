package com.timoteotorres;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example03 {

	public static void main(String[] args) {
		
		Flux.fromArray(new String [] {"Ana", "Luciana", "Paola", "Karen"})
			.flatMap(Example03::changeNameInMono)
			.subscribe(System.out::println);
		
	}
	
	private static Mono<String> changeNameInMono(String name){
		return Mono.just(name.concat(" modified"));
	}
	
}
