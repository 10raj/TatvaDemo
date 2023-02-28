//package com.example.GateWay.securityUpdated;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import lombok.RequiredArgsConstructor;
//
//
//@Configuration 
//@RequiredArgsConstructor
//public class SecurityConfig{
//	
//	private final JwtAthFilter jwtAthFilter;
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//		.authorizeHttpRequests((authz) -> authz
//				.requestMatchers("/**").permitAll()
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