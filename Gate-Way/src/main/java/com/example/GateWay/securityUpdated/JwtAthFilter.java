//package com.example.GateWay.securityUpdated;
//
//import java.io.IOException;
//import java.util.Collection;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAthFilter extends OncePerRequestFilter{
//	
//	private final UserDetailsService userDetailsService;
//	private final JwtUtil jwtUtil;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		final String jwtToken;
//		final String userName;
//		final String requestHeader = request.getHeader("AUTHORIZATION");
//		
//		if(requestHeader==null || ! requestHeader.startsWith("Bearer")) {
//			throw new RuntimeException("Missing Authorisation Header");
//		}
//		else {
//		
//		jwtToken = requestHeader.substring(7);
//		userName= jwtUtil.extractUsername(jwtToken);
//		
//		if(userName!= null || SecurityContextHolder.getContext().getAuthentication()!= null ) {
//			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//			if(jwtUtil.validateToken(jwtToken, userDetails)) {
//				
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, (Collection<? extends GrantedAuthority>) userDetails.getAuthorities());
//              usernamePasswordAuthenticationToken
//                      .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//		}
//		filterChain.doFilter(request, response);
//		}
//	}
//	
//
//}