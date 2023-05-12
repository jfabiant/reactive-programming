package com.timoteotorres.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.timoteotorres.domain.Contact;
import reactor.core.publisher.Mono;

public interface ContactRepository extends ReactiveMongoRepository<Contact, String>{
	Mono<Contact> findFirstByEmail(String email);
	Mono<Contact> findAllByPhoneOrName(String phoneOrFirstname);
}
