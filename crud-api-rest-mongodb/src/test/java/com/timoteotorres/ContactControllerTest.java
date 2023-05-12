package com.timoteotorres;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import com.timoteotorres.domain.Contact;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	private Contact contactSaved;
	
	@Test
	@Order(0)
	public void testSaveContact() {
		Flux<Contact> contactFlux = webTestClient.post()
				.uri("/functional/contacts")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(new Contact("webtest", "wt@gmail.com", "224")))
				.exchange()
				.expectStatus().isAccepted()
				.returnResult(Contact.class)
				.getResponseBody()
				.log();
		contactFlux.next().subscribe(contact -> {
			this.contactSaved = contact;
		});
		
		Assertions.assertNotNull(contactFlux);
	}
	
	@Test
	@Order(1)
	public void testFindContactByEmail() {
		Flux<Contact> contactFlux = webTestClient.get()
				.uri("/functional/contacts/byEmail/{email}", "wt@gmail.com")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isOk()
				.returnResult(Contact.class)
				.getResponseBody()
				.log();
		
		StepVerifier.create(contactFlux)
					.expectSubscription()
					.expectNextMatches(contact -> contact.getEmail().equals("wt@gmail.com"))
					.verifyComplete();
	}
	
	@Test
	@Order(2)
	public void testUpdateContact() {
		Flux<Contact> contactFlux = webTestClient.put()
				.uri("/functional/contacts/{id}", this.contactSaved.getId())
				.accept(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(new Contact(this.contactSaved.getId(),"WebTestClientUpdated", "wtcu@gmail.com", "999999")))
				.exchange()
				.returnResult(Contact.class)
				.getResponseBody()
				.log();
		
		StepVerifier.create(contactFlux)
					.expectSubscription()
					.expectNextMatches(contact -> contact.getEmail().equals("wtcu@gmail.com"))
					.verifyComplete();
	}
	
	@Test
	@Order(3)
	public void testContactList() {
		Flux<Contact> contactFlux = webTestClient.get()
				.uri("/functional/contacts")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.returnResult(Contact.class)
				.getResponseBody()
				.log();
		
		StepVerifier.create(contactFlux)
		.expectSubscription()
		.expectNextCount(1)
		.verifyComplete();
	}
	
	@Test
	@Order(4)
	public void testDeleteContact() {
		Flux<Void> voidFlux = this.webTestClient.delete()
				.uri("/functional/contacts/{id}", this.contactSaved.getId())
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.returnResult(Void.class)
				.getResponseBody();
		StepVerifier.create(voidFlux)
		.expectSubscription()
		.verifyComplete();
	}
	
}
