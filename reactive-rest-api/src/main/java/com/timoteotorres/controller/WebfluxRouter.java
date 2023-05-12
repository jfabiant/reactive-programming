package com.timoteotorres.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WebfluxRouter {
	
	@Bean
	RouterFunction<ServerResponse> functionalRoutes(ProductHandler productHandler){
		return RouterFunctions.route(RequestPredicates.GET("/functional/mono"), productHandler::showMessageMono)
				.andRoute(RequestPredicates.GET("/functional/flux"), productHandler::showMessageFlux);
	}
	
}
