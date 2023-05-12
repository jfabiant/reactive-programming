package com.timoteotorres;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ex03 {

	public static void main(String[] args) {
		
		Flux.just(2, 7, 10, 12, 0, 22, 40)
		.map(i -> {
			if(i==0) {
				throw new RuntimeException("Exception i == 0");
			}
			return i;
		})
		.concatWith(Mono.just(12))
		.onErrorContinue((ex, val) -> {
			System.out.println("Exception: "+ex);
			System.out.println("El elemento que causa el error es: "+val);
		})
		.log()
		.subscribe();
		
	}

}
