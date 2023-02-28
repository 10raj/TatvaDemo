package com.example.Readers.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService{

	
	public UserDetails loadUserByUsername(String username, HttpServletRequest request) throws UsernameNotFoundException {
		UserDetails currentUser = User.withUsername(request.getUserPrincipal().getName()).roles("admin").build();
		return currentUser;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
