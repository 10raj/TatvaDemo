package com.example.kafka.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class kafkaListener {

	@KafkaListener(
			topics="testTopic",
			groupId = "groupId"
			)
	public void listeners(String data) {
		System.out.println("kafka  :: "+ data);
	}
	
}
