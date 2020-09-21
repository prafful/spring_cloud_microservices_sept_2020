package com.example.demo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Friend;

@Service
public class ConsumerListener {

	@KafkaListener(topics = "topic1", groupId = "group_id")
	public void consumeMessage(String message) {
		System.out.println("Message received in consumer: "+ message);
	}
	
	@KafkaListener(topics = "topic1", groupId = "group_json", containerFactory = "kafkaListenerFriendContainerFactory")
	public void consumeFriend(Friend friend) {
		System.out.println("Consumed friend: " + friend);
	}
}
