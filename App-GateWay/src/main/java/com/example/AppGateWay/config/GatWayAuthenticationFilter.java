package com.example.AppGateWay.config;

import java.time.Duration;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.JwtVerificationException;
import com.okta.jwt.JwtVerifiers;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GatWayAuthenticationFilter extends AbstractGatewayFilterFactory<GatWayAuthenticationFilter.Config> {
	public static class Config {
		public OAuth2AuthorizedClientManager manager;

		public Config(OAuth2AuthorizedClientManager manager) {
	        this.manager = manager;
	    }
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			AccessTokenVerifier jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
				    .setIssuer("https://dev-59505644.okta.com/oauth2/default")
				    .setAudience("api://default")                // defaults to 'api://default'
				    .setConnectionTimeout(Duration.ofSeconds(1))      // defaults to 1s
				    .build();
			
			try {
				
				 
				log.info(exchange.getRequest().getHeaders().getFirst("Authorization"));
				Object claims =jwtVerifier.decode(exchange.getRequest().getHeaders().getFirst("Authorization").substring(7)).getClaims().get("Claims");
				log.info(claims.toString());
				
				String token = config.manager.authorize(OAuth2AuthorizeRequest
		        		.withClientRegistrationId("my-internal-client")
		        		.principal("internal").build()).getAccessToken()
		        		.getTokenValue();

		        log.info("Rest Template interceptor: Token :  {} ",token);
		      //  request.getHeaderNames().add("Authorization","Bearer "+token);
			} catch (JwtVerificationException e) {
				log.error("Error while getting JWT");
				e.printStackTrace();
			}
			return chain.filter(exchange);
		});
	}
}

