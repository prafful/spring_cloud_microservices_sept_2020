package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.ProductEntity;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;

	public List<ProductEntity> getAllProducts() {
		// TODO Auto-generated method stub
		return productDao.getAllProducts();
	}

	public List<ProductEntity> addProduct(ProductEntity pe) {
		// TODO Auto-generated method stub
		return productDao.addProduct(pe);
	}

	public ProductEntity getProductByCode(String productCode) {
		// TODO Auto-generated method stub
		return productDao.getProductByCode(productCode);
	}

}
