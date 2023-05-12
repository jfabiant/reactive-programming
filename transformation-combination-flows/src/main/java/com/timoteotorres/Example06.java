package com.timoteotorres;

import reactor.core.publisher.Flux;

public class Example06 {

	public static void main(String[] args) {
		
		Flux<String> f1 = Flux.just("A", "B", "C");
		Flux<String> f2 = Flux.just("D", "E", "F");
		
		Flux.zip(f1, f2, (val1,val2) -> val1 + val2)
			.subscribe(System.out::println);
		
	}

}
