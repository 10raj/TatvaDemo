package com.example.GateWay.service;

import org.springframework.http.ResponseEntity;
import com.example.GateWay.dtos.LogInRequest;
import com.example.GateWay.dtos.UserRequestDto;
import com.example.GateWay.dtos.UserValidationDto;

public interface AuthenticationService {

	ResponseEntity<String> authentication(LogInRequest jwtRequest);

	ResponseEntity<String> registration(UserRequestDto userRequestDto);

	ResponseEntity<String> validation(UserValidationDto userValidationDto);

}
