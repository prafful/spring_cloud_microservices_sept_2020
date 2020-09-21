package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.demo.pojo.Friend;

@EnableKafka
@Configuration
public class KafkaConfig {
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		Map<String, Object> configs = new HashMap<>();
		configs.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , "localhost:9092");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		
		
		return new DefaultKafkaConsumerFactory<>(configs);
	}
	
	
	@Bean 
	public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	
	@Bean
	public ConsumerFactory<String, Friend> consumerFriendFactory(){
		Map<String, Object> configs = new HashMap<>();
		configs.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , "localhost:9092");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		
		
		return new DefaultKafkaConsumerFactory<String, Friend>
										(configs, 
										new StringDeserializer(), 
										new JsonDeserializer<>(Friend.class));
				
	}
	

	@Bean 
	public ConcurrentKafkaListenerContainerFactory kafkaListenerFriendContainerFactory(){
		ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFriendFactory());
		return factory;
	}
	
	
	

}
