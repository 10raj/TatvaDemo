package com.example.GateWay.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.GateWay.dtos.LogInRequest;
import com.example.GateWay.dtos.UserRequestDto;
import com.example.GateWay.dtos.UserValidationDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService{

	private final RestTemplate restTemplate;
	
	
	@Override
	public ResponseEntity<String> authentication(LogInRequest jwtRequest) {
		String createPersonUrl = "http://localhost:9999/api/v1/authenticate";
		return restTemplate.postForEntity(createPersonUrl, jwtRequest, String.class);
	}
	@Override
	public ResponseEntity<String> validation(UserValidationDto userValidationDto) {
		log.info("validation method called");
		String createPersonUrl = "http://localhost:9999/api/v1/validate";
		return restTemplate.postForEntity(createPersonUrl, userValidationDto, String.class);
	}


	@Override
	public ResponseEntity<String> registration(UserRequestDto userRequestDto) {
		log.info("registration method called");
		String createPersonUrl = "http://localhost:9999/api/v1/user";
		return restTemplate.postForEntity(createPersonUrl, userRequestDto, String.class);
	}

}
