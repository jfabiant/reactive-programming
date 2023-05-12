package com.timoteotorres;

import reactor.core.publisher.Flux;

public class Example04 {

	public static void main(String[] args) {
		
		Flux<String> f1 = Flux.fromArray(new String[] {"a", "b", "c"});
		Flux<String> f2 = Flux.fromArray(new String[] {"d", "e", "f"});
		
		Flux<String> f3 = Flux.concat(f1, f2);
		
		f3.subscribe(e -> System.out.println(e));
		
	}
	
}
