package com.timoteotorres.services;

import org.springframework.stereotype.Service;

import com.timoteotorres.dao.ProductRepository;
import com.timoteotorres.domain.Product;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public Flux<Product> findAll(){
		Flux<Product> f1 = this.productRepository.findAll();
		Flux<Product> f2 = this.productRepository.findOthers();
		return Flux.merge(f1,f2);
	}
	
}
