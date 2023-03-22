package com.example.AppGateWay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

	@Autowired
	private AuthManager jwtAuthManager;
	
	@Autowired
	private AuthConverter authConverter;
	
@Bean
public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
	log.info("filter CHain called");
	AuthenticationWebFilter jwtFilter = new AuthenticationWebFilter(jwtAuthManager);
	jwtFilter.setServerAuthenticationConverter(authConverter);
    return http
    		.csrf().disable()
    		.formLogin().disable()
    		.httpBasic().disable()
            .authorizeExchange()
            .pathMatchers("/api/v1/user/**").permitAll()
            .pathMatchers("/auth/**").permitAll()
            .pathMatchers("/readers/**").hasRole("ADMIN")
            .pathMatchers("/book/**").hasAnyRole("ADMIN")
            .and()
            //.authorizeExchange().anyExchange().authenticated().and()
            .addFilterAt(jwtFilter,SecurityWebFiltersOrder.AUTHENTICATION)
            .build();
	}
}