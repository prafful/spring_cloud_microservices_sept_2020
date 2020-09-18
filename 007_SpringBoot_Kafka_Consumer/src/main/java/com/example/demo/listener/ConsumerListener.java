package com.example.demo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerListener {

	@KafkaListener(topics = "topic1", groupId = "group_id")
	public void consumeMessage(String message) {
		System.out.println("Message received in comsumer: "+ message);
	}
	
}
