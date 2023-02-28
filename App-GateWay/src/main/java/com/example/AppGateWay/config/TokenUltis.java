package com.example.AppGateWay.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import com.okta.commons.lang.Strings;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtIssuerValidator;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenUltis {

	 static Collection<? extends GrantedAuthority> tokenScopesToAuthorities(OAuth2AccessToken accessToken) {

	        if (accessToken == null || accessToken.getScopes() == null) {
	            return Collections.emptySet();
	        }

	        return accessToken.getScopes().stream()
	                .map(scope -> "SCOPE_" + scope)
	                .map(SimpleGrantedAuthority::new)
	                .collect(Collectors.toSet());
	    }

	    static Collection<? extends GrantedAuthority> tokenClaimsToAuthorities(Map<String, Object> attributes, String claimKey) {

	        if (!CollectionUtils.isEmpty(attributes) && StringUtils.hasText(claimKey)) {
	            Object rawRoleClaim = attributes.get(claimKey);
	            if (rawRoleClaim instanceof Collection) {
	                return ((Collection<String>) rawRoleClaim).stream()
	                        .map(SimpleGrantedAuthority::new)
	                        .collect(Collectors.toSet());
	            } else if (rawRoleClaim != null) { // don't log when null, that is the default condition
	               log.debug("Could not extract authorities from claim '{}', value was not a collection", claimKey);
	            }
	        }
	        return Collections.emptySet();
	    }

}
