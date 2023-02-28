package com.example.kafka.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher {

	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
	    kafkaTemplate.send("testTopic", msg);
	} 
	
	@Bean
	public void sending() {
		sendMessage("RajTesting kafkaf" );
	}
}
