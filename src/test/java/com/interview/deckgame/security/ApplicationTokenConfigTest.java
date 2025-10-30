package com.interview.deckgame.security;

import java.util.Set;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;

class ApplicationTokenConfigTest implements WithAssertions {

    private ApplicationContextRunner runner;

    @BeforeEach
    public void setup() {
        runner = new ApplicationContextRunner()
                .withPropertyValues("goto.application-token=game-deck")
                .withBean(HttpServletRequest.class, MockHttpServletRequest::new)
                .withUserConfiguration(ApplicationTokenConfig.class);
    }

    @Test
    public void testValidateApplicationTokenAspectBean() {
        runner.run(context -> {
            assertThat(context.getBean("validateApplicationTokenAspect")).isInstanceOfSatisfying(ValidateApplicationTokenAspect.class, aspect -> {
                assertThat(aspect).extracting("applicationToken").isInstanceOfSatisfying(String.class, token -> {
                    assertThat(token).isEqualTo("game-deck");
                });
                assertThat(aspect).extracting("request").isSameAs(context.getBean("httpServletRequest"));
            });
        });
    }
}
