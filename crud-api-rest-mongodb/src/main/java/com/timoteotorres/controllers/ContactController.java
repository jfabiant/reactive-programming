package com.timoteotorres.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.timoteotorres.domain.Contact;
import com.timoteotorres.repository.ContactRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1.0")
@RequiredArgsConstructor
public class ContactController {
	
	private final ContactRepository contactRepository;
	
	@GetMapping("/contacts")
	public Flux<Contact> getContactList(){
		return this.contactRepository.findAll();
	}
	
	@GetMapping("/contact/{id}")
	public Mono<ResponseEntity<Contact>> getContact(@PathVariable String id){
		return this.contactRepository.findById(id)
				.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/contacts/byEmail/{email}")
	public Mono<ResponseEntity<Contact>> getContactByEmail(@PathVariable String email){
		return this.contactRepository.findFirstByEmail(email)
				.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/contacts")
	public Mono<ResponseEntity<Contact>> saveContact(@RequestBody Contact contact){
		return this.contactRepository.insert(contact)
				.map(c -> new ResponseEntity<>(c, HttpStatus.ACCEPTED))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
	}
	
	@PutMapping("/contacts/{id}")
	public Mono<ResponseEntity<Contact>> updateContact(@PathVariable String id,
			@RequestBody Contact contact){
		return this.contactRepository.findById(id)
				.flatMap(c -> {
					contact.setId(id);
					return this.contactRepository.save(contact)
							.map(c1 -> new ResponseEntity<>(c1,HttpStatus.ACCEPTED));
				})
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/contacts/{id}")
	public Mono<Void> deleteContact(@PathVariable String id){
		return this.contactRepository.deleteById(id);
	}
	
}
