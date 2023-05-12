package com.timoteotorres.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "contact")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contact {

	@Id
	private String id;
	private String name;
	private String email;
	private String phone;
	
	public Contact(String name, String email, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
}
