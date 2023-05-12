package com.timoteotorres;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ExampleTest {

	@Test
	public void testTransformMap() {

		List<String> nameList = Arrays.asList("Google", "ABC", "Fb", "Stackoverflow");
		Flux<String> namesFlux = Flux.fromIterable(nameList).filter(name -> name.length() > 5)
				.map(name -> name.toUpperCase()).log();
		StepVerifier.create(namesFlux).expectNext("GOOGLE", "STACKOVERFLOW").verifyComplete();

	}

	@Test
	void testTransformFlatMap() {
		List<String> nameList = Arrays.asList("Google", "ABC", "Fb", "Stackoverflow");
		Flux<String> namesFlux = Flux.fromIterable(nameList).filter(name -> name.length() > 5).flatMap(name -> {
			return Mono.just(name.toUpperCase());
		}).log();
		StepVerifier.create(namesFlux).expectNext("GOOGLE", "STACKOVERFLOW").verifyComplete();
	}

	@Test
	void testUsingMerge() {

		Flux<String> flux1 = Flux.just("a", "b", "c");
		Flux<String> flux2 = Flux.just("d", "e", "f");

		Flux<String> fluxMerge = Flux.merge(flux1, flux2).log();

		StepVerifier.create(fluxMerge).expectSubscription().expectNext("a", "b", "c", "d", "e", "f").verifyComplete();

	}

	@Test
	void testUsingMergeWithDelay() {

		Flux<String> flux1 = Flux.just("a", "b", "c").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 = Flux.just("d", "e", "f").delayElements(Duration.ofSeconds(1));

		Flux<String> fluxMerge = flux1.mergeWith(flux2);

		StepVerifier.create(fluxMerge)
					.expectSubscription()
					.expectNextCount(6)
					.verifyComplete();
	}

	@Test
	void testUsingOperatorConcat() {

		Flux<String> flux1 = Flux.just("a", "b", "c").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 = Flux.just("d", "e", "f").delayElements(Duration.ofSeconds(1));

		Flux<String> fluxConcat = Flux.concat(flux1, flux2).log();

		StepVerifier.create(fluxConcat).expectSubscription().expectNext("a", "b", "c", "d", "e", "f").verifyComplete();

	}

	@Test
	void testUsingZip() {
		
		Flux<String> flux1 = Flux.just("a", "b", "c").delayElements(Duration.ofSeconds(1));
		Flux<String> flux2 = Flux.just("d", "e", "f").delayElements(Duration.ofSeconds(1));

		Flux<String> fluxConcat = Flux.zip(flux1,flux2,(f1, f2) -> {
			return f1.concat(" ").concat(f2);
		}).log();

		StepVerifier.create(fluxConcat)
					.expectSubscription()
					.expectNext("a d", "b e", "c f")
					.verifyComplete();
		
	}

}
