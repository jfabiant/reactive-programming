package com.timoteotorres.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import com.timoteotorres.dao.ProductRepository;
import com.timoteotorres.services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@GetMapping("/products")
	public String productList(Model model) {
		IReactiveDataDriverContextVariable reactiveList = 
				new ReactiveDataDriverContextVariable(this.productService.findAll());
		model.addAttribute("productList",reactiveList);
		return "products";
	}
	
}
