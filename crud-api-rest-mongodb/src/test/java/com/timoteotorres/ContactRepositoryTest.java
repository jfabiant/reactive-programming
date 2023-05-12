package com.timoteotorres;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import com.timoteotorres.domain.Contact;
import com.timoteotorres.repository.ContactRepository;

import reactor.core.publisher.*;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactRepositoryTest {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private ReactiveMongoOperations mongoOperations;
	
	@BeforeAll
	public void inserData() {
		Contact contact1 = new Contact();
		contact1.setName("Aba");
		contact1.setEmail("aba@gmail.com");
		contact1.setPhone("123");
		
		Contact contact2 = new Contact();
		contact2.setName("Roni");
		contact2.setEmail("roni@gmail.com");
		contact2.setPhone("456");
		
		Contact contact3 = new Contact();
		contact3.setName("Pala");
		contact3.setEmail("pala@gmail.com");
		contact3.setPhone("789");
		
		Contact contact4 = new Contact();
		contact4.setName("Leo");
		contact4.setEmail("leo@gmail.com");
		contact4.setPhone("9393");
		
		StepVerifier.create(this.contactRepository.insert(contact1).log())
					.expectSubscription()
					.expectNextCount(1)
					.verifyComplete();
		
		StepVerifier.create(this.contactRepository.save(contact2).log())
		.expectSubscription()
		.expectNextCount(1)
		.verifyComplete();
		
		StepVerifier.create(this.contactRepository.save(contact3).log())
		.expectSubscription()
		.expectNextCount(1)
		.verifyComplete();
		
		StepVerifier.create(this.contactRepository.save(contact4).log())
		.expectSubscription()
		.expectNextCount(1)
		.verifyComplete();
		
	}
	
	@Test
	@Order(1)
	public void testListContacts() {
		StepVerifier.create(this.contactRepository.findAll().log())
					.expectSubscription()
					.expectNextCount(4)
					.verifyComplete();			
	}
	
	@Test
	@Order(2)
	public void testFindByEmail() {
		StepVerifier.create(this.contactRepository.findFirstByEmail("pala@gmail.com").log())
					.expectSubscription()
					.expectNextMatches(c -> c.getEmail().equals("pala@gmail.com"))
					.verifyComplete();			
	}
	
	@Test
	@Order(3)
	public void testUpdateContext() {
		Mono<Contact> contactUpdated = this.contactRepository.findFirstByEmail("roni@gmail.com")
				.map(contact -> {
					contact.setPhone("8181");
					return contact;
				})
				.flatMap(contact -> {
					return this.contactRepository.save(contact);
				});
		
		StepVerifier.create(contactUpdated.log())
					.expectSubscription()
					.expectNextMatches(contact -> contact.getPhone().equals("8181"))
					.verifyComplete();
	}
	
	@Test
	@Order(4)
	public void testDeleteContactById() {
		Mono<Void> contactDeleted = this.contactRepository.findFirstByEmail("leo@gmail.com")
				.flatMap(contact -> {
					return this.contactRepository.deleteById(contact.getId());
				}).log();
		StepVerifier.create(contactDeleted)
					.expectSubscription()
					.verifyComplete();
	}
	
	@Test
	@Order(5)
	public void testDeleteContact() {
		Mono<Void> contactDeleted = this.contactRepository.findFirstByEmail("aba@gmail.com")
				.flatMap(contact -> this.contactRepository.delete(contact));
		StepVerifier.create(contactDeleted)
					.expectSubscription()
					.verifyComplete();
	}
	
	@AfterAll
	public void cleanData() {
		Mono<Void> elementsDeleted = this.contactRepository.deleteAll();
		StepVerifier.create(elementsDeleted.log())
					.expectSubscription()
					.verifyComplete();		
	}
	
}
