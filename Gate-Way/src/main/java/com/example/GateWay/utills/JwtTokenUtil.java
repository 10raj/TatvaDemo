package com.example.GateWay.utills;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.GateWay.config.JwtConfig;
import com.example.GateWay.dtos.RolesDto;
import com.example.GateWay.dtos.UserValidationDto;
import com.example.GateWay.exception.JwtTokenIncorrectStructureException;
import com.example.GateWay.exception.JwtTokenMalformedException;
import com.example.GateWay.exception.JwtTokenMissingException;
import com.example.GateWay.service.AuthenticationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenUtil {
	private final RestTemplate restTemplate;
	private final JwtConfig config;
	private final AuthenticationService authenticationService;

	public String generateToken(String id, String pass) {
		Map<String, Object> claimsMap = new HashMap<>();
		String url = "http://localhost:9999/api/v1/{id}";
		
		ResponseEntity<RolesDto[]> reponse= restTemplate.getForEntity(url, RolesDto[].class,id);
		if(reponse.getStatusCode()!=HttpStatus.OK)
			throw new RuntimeException("Unable to fetch User Roles");
		claimsMap.put("roles",reponse.getBody());
		Claims claims = Jwts.claims(claimsMap).setSubject(id);
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + config.getValidity() * 1000 * 60;
		Date exp = new Date(expMillis);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, config.getSecret()).compact();
	}

	public void validateToken(final String header) throws JwtTokenMalformedException, JwtTokenMissingException {
		 
		try {
			
			String[] parts = header.split(" ");
			if (parts.length != 2 || !"Bearer".equals(parts[0])) {
				throw new JwtTokenIncorrectStructureException("Incorrect Authentication Structure");
			}
			Claims claims = getAllClaimsFromToken(parts[1]);
			String username= claims.getSubject();
			List<RolesDto> roles = (List<RolesDto>) claims.get("roles");	
			UserValidationDto userValidationDto = new UserValidationDto(username,roles);
			Jwts.parser().setSigningKey(config.getSecret()).parseClaimsJws(parts[1]);
			ResponseEntity<String> response =authenticationService.validation(userValidationDto);
			if(response.getStatusCode()!=HttpStatus.OK)
				throw new RuntimeException("Unble to validate User and It's Roles");
			
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}
	
	private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(config.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("Could not get all claims Token from passed token");
            claims = null;
        }
        return claims;
    }
}
