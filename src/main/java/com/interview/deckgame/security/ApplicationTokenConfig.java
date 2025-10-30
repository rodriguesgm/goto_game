package com.interview.deckgame.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableConfigurationProperties(ApplicationTokenProperties.class)
public class ApplicationTokenConfig {

    @Bean
    ValidateApplicationTokenAspect validateApplicationTokenAspect(HttpServletRequest httpServletRequest,
            ApplicationTokenProperties applicationTokenProperties) {
        return new ValidateApplicationTokenAspect(httpServletRequest, applicationTokenProperties.getApplicationToken());
    }
}

