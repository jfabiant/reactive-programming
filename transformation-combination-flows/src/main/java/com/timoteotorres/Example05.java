package com.timoteotorres;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example05 {

	public static void main(String[] args) {
		
		Flux<String> f1 = Flux.fromArray(new String[] {"a", "b", "c"});
		Mono<String> m1 = Mono.just("f");
		
		Flux<String> f3 = f1.concatWith(m1);
		
		f3.subscribe(e -> System.out.println(e));
		
	}
	
}
