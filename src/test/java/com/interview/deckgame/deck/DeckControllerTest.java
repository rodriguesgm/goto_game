package com.interview.deckgame.deck;

import static org.mockito.Mockito.when;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.interview.deckgame.deck.internal.DeckEntity;
import com.interview.deckgame.deck.internal.DeckServiceImpl;
import com.interview.deckgame.deck.model.DeckDto;
import com.interview.deckgame.deck.model.DeckMapper;

@ExtendWith(MockitoExtension.class)
class DeckControllerTest implements WithAssertions {

    private DeckController controller;

    @Mock
    private DeckServiceImpl deckService;
    @Mock
    private DeckMapper deckMapper;

    @BeforeEach
    void setUp() {
        controller = new DeckController(deckService, deckMapper);
    }

    @Test
    void giveRequestWithName_whenCreate_thenReturnDeckDtoResponse() {
        final String deckName = "Test Deck";
        final var deckCreated = new DeckEntity();
        final var deckMapped = new DeckDto("id");
        when(deckService.newDeck(deckName)).thenReturn(deckCreated);
        when(deckMapper.toDto(deckCreated)).thenReturn(deckMapped);

        final var request = new DeckController.DeckCreateRequest(deckName);

        final var response = controller.create(request);

        assertThat(response).isEqualTo(new DeckDto("id"));
    }
}
