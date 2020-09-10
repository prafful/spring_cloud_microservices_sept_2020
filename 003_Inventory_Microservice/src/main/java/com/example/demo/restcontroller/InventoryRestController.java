package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.InventoryEntity;
import com.example.demo.service.InventoryService;

@RestController
public class InventoryRestController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping("/")
	public String welcome() {
		return "Welcome to Inventory Microservice!!!!";
	}
	
	@GetMapping("/all")
	public List<InventoryEntity> getAllInventory() {
		return inventoryService.getAllInventory();
	}
	
	@PostMapping("/add")
	public List<InventoryEntity> addInventory(@RequestBody InventoryEntity ie) {
		return inventoryService.addInventory(ie);
	}
	
	//http://localhost:9992/P001
	//http://localhost:9992/P002
	@GetMapping("/{code}")
	public InventoryEntity getInventoryByProductCode(@PathVariable String code) {
		return inventoryService.getInventoryByProductCode(code);
	}

}
