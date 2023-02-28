package com.example.Readers.config;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Readers.services.CustomeUserDetailsService;
import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.JwtVerificationException;
import com.okta.jwt.JwtVerifiers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class filter extends OncePerRequestFilter{
	
	private OAuth2AuthorizedClientManager manager;

	public filter(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		AccessTokenVerifier jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
			    .setIssuer("https://dev-59505644.okta.com/oauth2/default")
			    .setAudience("api://default")                // defaults to 'api://default'
			    .setConnectionTimeout(Duration.ofSeconds(1))      // defaults to 1s
			    .build();
		
		try {
			 
			log.info(request.getHeader("Authorization"));
			Object claims =jwtVerifier.decode(request.getHeader("Authorization").substring(7)).getClaims().get("Claims");
			log.info(claims.toString());
			
			String token = manager.authorize(OAuth2AuthorizeRequest
	        		.withClientRegistrationId("my-internal-client")
	        		.principal("internal").build()).getAccessToken()
	        		.getTokenValue();

	        log.info("Rest Template interceptor: Token :  {} ",token);
	      //  request.getHeaderNames().add("Authorization","Bearer "+token);
		} catch (JwtVerificationException e) {
			log.error("Error while getting JWT");
			e.printStackTrace();
		}
		
		
		  filterChain.doFilter(request, response);
	}

}
