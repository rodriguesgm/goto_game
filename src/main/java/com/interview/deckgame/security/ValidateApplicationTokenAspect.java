package com.interview.deckgame.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Aspect
@RequiredArgsConstructor
@Order(0)
public class ValidateApplicationTokenAspect {

    public static final String APPLICATION_TOKEN_HEADER_ID = "ApplicationToken";

    private final HttpServletRequest request;

    private final String applicationToken;

    @Before("@annotation(com.interview.deckgame.security.ValidateApplicationToken)")
    public void validateApplicationToken() {
        if (request.getHeader(APPLICATION_TOKEN_HEADER_ID) == null || !this.applicationToken.equals(
                request.getHeader(APPLICATION_TOKEN_HEADER_ID))) {
            throw new UnknownApplicationTokenException("Invalid application token");
        }
    }
}
