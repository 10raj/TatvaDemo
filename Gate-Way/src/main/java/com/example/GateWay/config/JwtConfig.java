package com.example.GateWay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties("jwt")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {
    private String secret;
    private long validity;
    private boolean authDisabled;
}