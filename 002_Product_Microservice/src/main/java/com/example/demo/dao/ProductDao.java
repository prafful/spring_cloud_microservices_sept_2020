package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.ProductEntity;
import com.example.demo.pojo.InventoryResponse;
import com.example.demo.repository.ProductRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<ProductEntity> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	public List<ProductEntity> addProduct(ProductEntity pe) {
		// TODO Auto-generated method stub
		productRepository.save(pe);
		return productRepository.findAll();
	}

	
	@HystrixCommand(fallbackMethod = "getProductByCodeFailed",
					commandProperties = {
						@HystrixProperty (name = "execution.isolation.thread.timeoutInMilliseconds", value = "8000")
					})
	public ProductEntity getProductByCode(String productCode) {
		// TODO Auto-generated method stub
		///connect with inventory microservice to get product status
		//check if product exists with given productCode
		ProductEntity tempProduct = productRepository.findByProductCode(productCode);	
		
		//String url = "http://localhost:9992/" + productCode;
		String url = "http://localhost:8080/shop/inventory/" + productCode;
		ResponseEntity<InventoryResponse> inventoryResponse 
					= restTemplate.getForEntity(url, InventoryResponse.class);
		System.out.println("Response received from inventory ms!!!!");
		System.out.println(inventoryResponse.toString());
		
		if(inventoryResponse.getStatusCode() == HttpStatus.OK) {
			if(inventoryResponse.getBody().getQuantity() > 0) {
				tempProduct.setStockStatus(true);
				productRepository.saveAndFlush(tempProduct);
			}else {
				tempProduct.setStockStatus(false);
				productRepository.saveAndFlush(tempProduct);
			}
		}
		
		return productRepository.findByProductCode(productCode);
	}
	
	public ProductEntity getProductByCodeFailed(String productCode) {
		System.out.println("################      Failed            ########################");
		ProductEntity tempProduct = productRepository.findByProductCode(productCode);	
		return tempProduct;
	}

}
