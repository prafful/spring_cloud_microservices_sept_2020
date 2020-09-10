package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.InventoryDao;
import com.example.demo.entity.InventoryEntity;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryDao inventoryDao;

	public List<InventoryEntity> getAllInventory() {
		// TODO Auto-generated method stub
		return inventoryDao.getAllInventory();
	}

	public List<InventoryEntity> addInventory(InventoryEntity ie) {
		// TODO Auto-generated method stub
		return inventoryDao.addInventory(ie);
	}

	public InventoryEntity getInventoryByProductCode(String productCode) {
		// TODO Auto-generated method stub
		return inventoryDao.getInventoryByProductCode(productCode);
	}

}
