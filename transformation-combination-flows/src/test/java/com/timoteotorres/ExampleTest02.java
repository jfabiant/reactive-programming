package com.timoteotorres;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ExampleTest02 {
	
	@Test
	void testMergeWith() {
		StepVerifier.create(returnMerge())
					.expectNext("a")
					.expectNext("c")
					.expectNext("b")
					.expectNext("d")
					.expectComplete()
					.verify();
	}
	
	private static Flux<String> returnMerge(){
		Flux<String> f1 = Flux.fromArray(new String[] {"a", "b"})
							.delayElements(Duration.ofSeconds(1));
		Flux<String> f2 = Flux.fromArray(new String[] {"c", "d"})
				.delayElements(Duration.ofSeconds(1));
		
		return f1.mergeWith(f2);
	}
	
}
