package com.timoteotorres;

import java.util.ArrayList;
import java.util.Arrays;

import reactor.core.publisher.Flux;

public class Example01 {
	
	public static void main(String[] args) {
		
		Flux<String> ciudades = Flux.fromIterable(
				new ArrayList<>(Arrays.asList("New York", "Toronto", "Paris", "Rome"))
				);
		
		ciudades.log().subscribe();
		
	}
	
}
