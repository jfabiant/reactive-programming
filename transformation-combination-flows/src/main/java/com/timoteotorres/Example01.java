package com.timoteotorres;

import reactor.core.publisher.Flux;

public class Example01 {

	public static void main(String[] args) {
		
		Flux.fromArray(new String [] {"Ana", "Lucia", "Paola", "Karen"})
			.map(String::toUpperCase)
			.subscribe(System.out::println);
		
	}
	
}
