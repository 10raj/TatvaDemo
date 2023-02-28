package com.example.AppGateWay.config;

import com.okta.spring.boot.oauth.AuthoritiesProvider;
import com.okta.spring.boot.oauth.config.OktaOAuth2Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//
//@Configuration
//public class AuthorityProvidersConfig {
//	
//
//    @Bean
//    AuthoritiesProvider tokenScopesAuthoritiesProvider() {
//        return (user, userRequest) -> TokenUltis.tokenScopesToAuthorities(userRequest.getAccessToken());
//    }
//
//    @Bean
//    AuthoritiesProvider groupClaimsAuthoritiesProvider(OktaOAuth2Properties oktaOAuth2Properties) {
//        return (user, userRequest) -> TokenUltis.tokenClaimsToAuthorities(user.getAttributes(), oktaOAuth2Properties.getGroupsClaim());
//    }
//}
