//package com.example.AppGateWay.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import lombok.RequiredArgsConstructor;
//
//
//@Configuration 
//@RequiredArgsConstructor
//public class SecurityConfig {
//	
//	private final JwtAthFilter jwtAthFilter;
//	
//	@Bean
//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//		.authorizeHttpRequests((authz) -> authz
//				.requestMatchers("/api/v1/user/**").permitAll()
//                .anyRequest().authenticated()
//            );
////			.requestMatchers("/**").permitAll()
////			.requestMatchers("/book/**").hasAnyAuthority("ROLE_SUPERADMIN")
////			.requestMatchers("/publisher/**").hasAnyAuthority("ROLE_STAFF")
////			.anyRequest()
////			.authenticated()
////			.and()
////			.addFilterBefore(jwtAthFilter, UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	}
//	
//	@Bean 
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//	
//}