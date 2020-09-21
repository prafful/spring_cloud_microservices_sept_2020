package com.example.demo.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Friend;

@RestController
@RequestMapping("kafka")
public class KafkaRestController {
	
	//@Autowired 
	//KafkaTemplate<String, String> kafkaTemplate1;
	
	@Autowired
	KafkaTemplate<String, Friend> kafkaTemplate;
	
	private static final String TOPIC = "topic1";

	/*
	 * @GetMapping("/publish/{message}") public String
	 * publishMessageToKafka(@PathVariable String message) {
	 * 
	 * kafkaTemplate1.send(TOPIC, message);
	 * 
	 * return "Publish is success!!!!"; }
	 */
	
	@GetMapping("/publish/friend")
	public String publishFriendToKafka() {
		
		kafkaTemplate.send(TOPIC, new Friend("OBB", "Chennai"));
		
		return "Publish Friend is success!!!!";
	}
	
}
