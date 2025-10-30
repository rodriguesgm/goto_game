package com.interview.deckgame.deck;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class DeckControllerIT implements WithAssertions {

    protected HttpHeaders headers;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
    }

    @Test
    void givenInvalidApplicationToken_whenCreate_thenReturnForbidden() throws Exception {
        headers.add("ApplicationToken", "invalid_token");

        callCreateDeck().andExpect(status().isForbidden());
    }

    @Test
    void givenValidRequestWithApplicationToken_whenCreate_thenReturnSuccess() throws Exception {
        headers.add("ApplicationToken", "default-token");

        callCreateDeck().andExpect(status().isOk());
    }

    private ResultActions callCreateDeck() throws Exception {
        return this.mockMvc.perform(post("/decks")
                .content("""
                        { "name": "Test Deck" }
                        """)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
        );
    }
}
