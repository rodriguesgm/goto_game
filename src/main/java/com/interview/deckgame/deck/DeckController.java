package com.interview.deckgame.deck;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.deck.internal.DeckServiceImpl;
import com.interview.deckgame.deck.model.DeckDto;
import com.interview.deckgame.deck.model.DeckMapper;
import com.interview.deckgame.security.ValidateApplicationToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/decks")
public class DeckController {

    private final DeckServiceImpl deckService;
    private final DeckMapper deckMapper;

    @PostMapping
    @ValidateApplicationToken
    public DeckDto create(@RequestBody DeckCreateRequest request) {
        return deckMapper.toDto(
                deckService.newDeck(request.name)
        );
    }

    public record DeckCreateRequest(String name) {
    }
}
