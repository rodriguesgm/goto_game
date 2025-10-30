package com.interview.deckgame.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Validated
@ConfigurationProperties(prefix = "goto")
public class ApplicationTokenProperties {
    @NotEmpty
    private String applicationToken;
}
