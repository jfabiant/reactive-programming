package com.timoteotorres;

import reactor.core.publisher.Flux;

public class Example02 {

	public static void main(String[] args) {
		
		Flux.fromArray(new String [] {"Ana", "Luciana", "Paola", "Karen"})
			.filter(n -> n.length() >= 5)
			.map(String::toUpperCase)
			.subscribe(System.out::println);
		
	}
	
}
