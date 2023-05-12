package com.timoteotorres.introduction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example02 {

	public static void main(String[] args) {
		
		Mono<String> mono = Mono.just("Hola");
		mono.subscribe(data -> System.out.println(data));
		
		Flux<String> flux = Flux.just("", "", "");
		
		mono.subscribe(System.out::println);
		
		mono.subscribe(data -> System.out.println(data), // onNext
				err -> System.out.println(err), // onError
				() -> System.out.println("Completed")); // onComplete
		
		
	}
	
}
