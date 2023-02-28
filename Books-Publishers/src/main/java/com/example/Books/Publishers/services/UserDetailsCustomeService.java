package com.example.Books.Publishers.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsCustomeService {

	
	public  UserDetails inMemoryUser(HttpServletRequest request, HttpServletResponse response) {
		String token  = request.getHeader("Authorization").substring(7);
 		UserDetails currentUser = User.withUsername(request.getUserPrincipal().getName())
 				.roles("admin").build();
 		return currentUser;
	}
}
