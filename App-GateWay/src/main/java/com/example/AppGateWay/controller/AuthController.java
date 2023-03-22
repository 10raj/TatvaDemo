package com.example.AppGateWay.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.AppGateWay.dtos.AuthResponse;
import com.example.AppGateWay.dtos.ReqResp;
import com.example.AppGateWay.dtos.UserModel;
import com.example.AppGateWay.service.AuthService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;

	@PostMapping("/login")
    public ResponseEntity<ReqResp<String>> login(@RequestBody UserModel userLogin) {
        log.info("Logging endpoint {}", userLogin.toString());
        return authService.login(userLogin.getUserName(), userLogin.getPassword())
                ;
    }
}