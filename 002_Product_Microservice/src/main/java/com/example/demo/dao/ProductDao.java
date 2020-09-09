package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductEntity> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	public List<ProductEntity> addProduct(ProductEntity pe) {
		// TODO Auto-generated method stub
		productRepository.save(pe);
		return productRepository.findAll();
	}

	
	public ProductEntity getProductByCode(String productCode) {
		// TODO Auto-generated method stub
		///connect with inventory microservice to get product status
		return null;
	}

}
