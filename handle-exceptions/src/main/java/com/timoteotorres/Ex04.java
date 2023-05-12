package com.timoteotorres;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ex04 {

	public static void main(String[] args) {
		
		Flux.just(2, 7, 10, 12, 0, 22, 40)
		.map(i -> {
			if(i==0) {
				throw new RuntimeException("Exception i == 0");
			}
			return i;
		})
		.onErrorMap((ex) -> {
			System.out.println("Exception: "+ex);
			return new CustomException(ex.getMessage(), ex);
		})
		.log()
		.subscribe();
		
	}

}
