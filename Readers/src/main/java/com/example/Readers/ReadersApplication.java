package com.example.Readers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReadersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadersApplication.class, args);
	}

}
