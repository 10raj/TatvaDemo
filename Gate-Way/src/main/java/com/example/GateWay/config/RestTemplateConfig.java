package com.example.GateWay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

	@Bean
	public RestTemplate generate(){
		return new RestTemplate();
	}
}
