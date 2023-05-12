package com.timoteotorres.introduction;

import reactor.core.publisher.Flux;

public class Ex04 {

	public static void main(String[] args) {
		
		Flux<String> flux = Flux.fromArray(new String[] {"data 1", "2", "3"});
		
		flux.doOnNext(data -> System.out.println(data)).subscribe();
		
	}

}
