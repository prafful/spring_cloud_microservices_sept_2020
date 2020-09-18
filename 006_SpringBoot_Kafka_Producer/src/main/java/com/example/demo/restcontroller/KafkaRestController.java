package com.example.demo.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaRestController {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String TOPIC = "topic1";

	@GetMapping("/publish/{message}")
	public String publishMessgeToKafka(@PathVariable String message) {
		
		kafkaTemplate.send(TOPIC, message);
		
		return "Publish is success!!!!";
	}
	
}
