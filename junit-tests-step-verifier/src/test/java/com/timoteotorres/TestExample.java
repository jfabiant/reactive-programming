package com.timoteotorres;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TestExample {

	@Test
	public void testFlux() {
		
		Flux<Integer> numbers = Flux.just(2,4,6,8,10);
		
		StepVerifier.create(numbers)
		.expectNext(2)
		.expectNext(4)
		.expectNext(6)
		.expectNext(8)
		.expectNext(10)
		.expectComplete()
		.verify();
		
	}
	
	@Test
	public void testFluxString() {
		
		Flux<String> names = Flux.just("John","Melisa","Brisa","Ana")
				.filter(n -> n.length() <= 5)
				.map(String::toUpperCase);
		
		StepVerifier.create(names)
		.expectNext("JOHN")
		.expectNext("BRISA")
		.expectNext("ANA")
		.expectComplete()
		.verify();
		
	}
	
}
