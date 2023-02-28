//package com.example.GateWay.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.example.GateWay.dtos.AuthenticationStatus;
//import com.example.GateWay.dtos.ErrorResponseDto;
//import com.example.GateWay.dtos.JwtResponse;
//import com.example.GateWay.dtos.LogInRequest;
//import com.example.GateWay.dtos.UserRequestDto;
//import com.example.GateWay.securityUpdated.JwtUtil;
//import com.example.GateWay.service.AuthenticationService;
//import com.example.GateWay.utills.JwtTokenUtil;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController("/gateway")
//@RequiredArgsConstructor
//public class JwtAuthenticationController 
//{
//
//	private final AuthenticationService authenticationService;
//	private final JwtTokenUtil jwtTokenUtil;
//	private final AuthenticationManager authenticationManager; 
//	private final JwtUtil jwtUtil;
//	private final UserDetailsService userDetailsService;
//
//	@RequestMapping(value="/login1", method = RequestMethod.POST)
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody LogInRequest authenticationRequest) {
//		AuthenticationStatus status = authenticate(authenticationRequest);
//
//		if (!status.getIsAuthenticated()) {
//			List<String> details = new ArrayList<>();
//			details.add(status.getMessage());
//			ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details, "uri");
//			return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
//		}
//
//		final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//		return ResponseEntity.ok(new JwtResponse(token));
//	}
//
//	private AuthenticationStatus authenticate(LogInRequest jwtRequest) {
//		AuthenticationStatus status;
//		ResponseEntity<String> response = authenticationService.authentication(jwtRequest);
//		
//		if (response.getStatusCode()!=HttpStatus.OK) {
//			status = new AuthenticationStatus(false, response.getBody());
//		}
//		else {
//			status = new AuthenticationStatus(true, response.getBody());
//		}
//
//		return status;
//	}
//	
//	@PostMapping("/login")
//	public ResponseEntity<String> Authentication(@RequestBody LogInRequest logInRequest){
//		authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(logInRequest.getUsername(), logInRequest.getPassword()));
//		try {
//			final UserDetails userDetails = userDetailsService.loadUserByUsername(logInRequest.getUsername());
//			return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
//		}
//		catch (Exception e) {
//			return ResponseEntity.status(400).body("unauthenticated user ::" + logInRequest.getUsername());
//		}
//	}
//}
