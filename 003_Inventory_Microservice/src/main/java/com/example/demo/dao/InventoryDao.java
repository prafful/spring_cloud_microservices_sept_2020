package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.InventoryEntity;
import com.example.demo.repository.InventoryRepository;

@Repository
public class InventoryDao {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	public List<InventoryEntity> getAllInventory() {
		// TODO Auto-generated method stub
		return inventoryRepository.findAll();
	}

	public List<InventoryEntity> addInventory(InventoryEntity ie) {
		// TODO Auto-generated method stub
		inventoryRepository.save(ie);
		return inventoryRepository.findAll();
	}

	
	public InventoryEntity getInventoryByProductCode(String productCode) {
		// TODO Auto-generated method stub
		///connect with inventory microservice to get product status
		return inventoryRepository.findByProductCode(productCode);
	}

}
