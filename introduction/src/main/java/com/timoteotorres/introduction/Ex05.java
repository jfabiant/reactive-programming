package com.timoteotorres.introduction;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Ex05 {

	public static void main(String[] args) {

		Flux<String> firstnames = Flux.just("Erika Mendoza", "Javier Ramirez", "Brisa Dominguez", "Alberto Llanos", 
				"Tini Dominguez", "Federico Dominguez");
		Flux<User> users = firstnames.map(name -> new User(name.split(" ")[0], name.split(" ")[1]))
				.filter(user -> user.getLastname().equalsIgnoreCase("Dominguez"))
				.doOnNext(user -> {
					if (user == null) {
						throw new RuntimeException("The names cannot be empty");
					}
				});

		users.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()));

	}

}
