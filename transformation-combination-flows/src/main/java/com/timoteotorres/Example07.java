package com.timoteotorres;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example07 {

	public static void main(String[] args) {
		
		Flux<Integer> f1 = Flux.just(3, 4, 5, 9);
		Flux<Integer> m1 = Flux.just(3, 10, 11);
		
		f1.zipWith(m1, (val1, val2) -> val1*val2).subscribe(System.out::println);
		
	}

}
