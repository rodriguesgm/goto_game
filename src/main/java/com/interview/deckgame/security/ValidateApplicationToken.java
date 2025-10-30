package com.interview.deckgame.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.HEADER, name = ValidateApplicationTokenAspect.APPLICATION_TOKEN_HEADER_ID, required = true, style = ParameterStyle.SIMPLE, description = "application token")
public @interface ValidateApplicationToken {
}
