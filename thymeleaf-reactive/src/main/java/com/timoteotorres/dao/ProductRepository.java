package com.timoteotorres.dao;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.timoteotorres.domain.Product;

import reactor.core.publisher.Flux;

@Repository
public class ProductRepository {

	private static List<Product> productList = new ArrayList<>();
	private static List<Product> productList2 = new ArrayList<>();
	
	static {
		productList.add(new Product(1, "Laptop", 5000));
		productList.add(new Product(2, "Monitor", 500));
		productList.add(new Product(3, "Ventilador", 300));
		productList2.add(new Product(4, "Celular", 800));
		productList2.add(new Product(5, "Lampara", 80));
		productList2.add(new Product(6, "Cama", 300));
	}
	
	public Flux<Product> findAll(){
		return Flux.fromIterable(productList).delayElements(Duration.ofSeconds(3));
	}
	
	public Flux<Product> findOthers(){
		return Flux.fromIterable(productList2).delayElements(Duration.ofSeconds(3));
	}
	
}
