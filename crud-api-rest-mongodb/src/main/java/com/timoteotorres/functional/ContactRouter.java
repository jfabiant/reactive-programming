package com.timoteotorres.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ContactRouter {

	@Bean
	RouterFunction<ServerResponse> contactRoute(ContactHandler contactHandler){
		return RouterFunctions.route(GET("/functional/contacts"), contactHandler::listContacts)
				.andRoute(GET("/functional/contacts/{id}"), contactHandler::getContactById)
				.andRoute(GET("/functional/contacts/byEmail/{email}"), contactHandler::getContactByEmail)
				.andRoute(POST("/functional/contacts"), contactHandler::saveContact)
				.andRoute(PUT("/functional/contacts/{id}"), contactHandler::updateContact)
				.andRoute(DELETE("/functional/contacts/{id}"), contactHandler::deleteContact);
	}
	
}
