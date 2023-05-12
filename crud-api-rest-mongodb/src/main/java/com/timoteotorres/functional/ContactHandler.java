package com.timoteotorres.functional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;

import com.timoteotorres.domain.Contact;
import com.timoteotorres.repository.ContactRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.*;
import static org.springframework.web.reactive.function.BodyInserters.*;

@Component
@RequiredArgsConstructor
public class ContactHandler {

	private final ContactRepository contactRepository;
	
	private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
	private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();
	
	public Mono<ServerResponse> listContacts(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.contactRepository.findAll(), Contact.class);
	}
	
	public Mono<ServerResponse> getContactById(ServerRequest request){
		
		String id = request.pathVariable("id");
		
		return this.contactRepository.findById(id)
				.flatMap(contact -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(contact)))
				.switchIfEmpty(response404);
	}
	
	public Mono<ServerResponse> getContactByEmail(ServerRequest request){
		
		String email = request.pathVariable("email");
		
		return this.contactRepository.findFirstByEmail(email)
				.flatMap(contact -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(contact)))
				.switchIfEmpty(response404);
		
	}
	
	public Mono<ServerResponse> saveContact(ServerRequest request){
		Mono<Contact> contactMono = request.bodyToMono(Contact.class);
		
		return contactMono
				.flatMap(c -> this.contactRepository.save(c)
						.flatMap(contactSaved -> ServerResponse.accepted()
										.contentType(MediaType.APPLICATION_JSON)
										.body(fromValue(contactSaved))))
				.switchIfEmpty(response406);
		
	}
	
	public Mono<ServerResponse> updateContact(ServerRequest request){
		Mono<Contact> contactMono = request.bodyToMono(Contact.class);
		String id = request.pathVariable("id");
		
		Mono<Contact> contactUpdated = contactMono.flatMap(contact -> 
		this.contactRepository.findById(id).flatMap(contactFound -> {
			contactFound.setPhone(contact.getPhone());
			contactFound.setName(contact.getName());
			contactFound.setEmail(contact.getEmail());
			return this.contactRepository.save(contactFound);
		}));
		
		return contactUpdated.flatMap(contact -> ServerResponse.accepted()
												.contentType(MediaType.APPLICATION_JSON)
												.body(fromValue(contact)))
				.switchIfEmpty(response404);
		
	}
	
	public Mono<ServerResponse> deleteContact(ServerRequest request){
		
		String id = request.pathVariable("id");
		
		Mono<Void> deletedContact = this.contactRepository.deleteById(id);
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(deletedContact, Void.class);
		
	}
	
}
