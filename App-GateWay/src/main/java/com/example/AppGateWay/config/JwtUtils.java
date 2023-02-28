package com.example.AppGateWay.config;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtils {

	@Value("${okta.oauth2.client-secret}")
	private static String SECRET;
	
	public  static String[] getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            log.info("claims::{}",claims.toString());
        } catch (Exception e) {
            log.error("Could not get all claims Token from passed token");
            claims = null;
        }
        return null;
    }
}
