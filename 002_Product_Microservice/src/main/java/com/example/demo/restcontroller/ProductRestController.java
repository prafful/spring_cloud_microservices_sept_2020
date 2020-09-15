package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@ConfigurationProperties
public class ProductRestController {
	
	@Value("${welcome.message}")
	private String message;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String welcome() {
		return message;
	}
	
	@GetMapping("/all")
	public List<ProductEntity> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/add")
	public List<ProductEntity> addProduct(@RequestBody ProductEntity pe) {
		return productService.addProduct(pe);
	}
	
	//http://localhost:9991/P001
	//http://localhost:9991/P002
	@HystrixCommand
	@GetMapping("/{code}")
	public ProductEntity getProductByCode(@PathVariable String code) {
		return productService.getProductByCode(code);
	}

}
