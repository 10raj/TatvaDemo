package com.example.AppGateWay;

import org.springframework.beans.factory.annotation.Autowired;
import com.okta.spring.boot.oauth.AuthoritiesProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;


@SpringBootApplication
@EnableDiscoveryClient
public class AppGateWayApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(AppGateWayApplication.class, args);
		
	}
}
