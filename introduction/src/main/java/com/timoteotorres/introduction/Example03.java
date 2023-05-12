package com.timoteotorres.introduction;

import reactor.core.publisher.Mono;

public class Example03 {

	public static void main(String[] args) {
		
		Mono<String> mono = Mono.fromSupplier(() -> {
			throw new RuntimeException("Exception ocurred!");
		});
		
		mono.subscribe(data -> System.out.println(data),
				err -> System.out.println(err),
				() -> System.out.println("Completed"));
		
	}

}
