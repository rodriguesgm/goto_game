package com.interview.deckgame.game;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.game.internal.GameService;
import com.interview.deckgame.game.model.GameDto;
import com.interview.deckgame.game.model.GameMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games/{gameId}/decks")
public class GameDeckController {

    private final GameMapper gameMapper;
    private final GameService gameService;

    @PostMapping
    public GameDto addDeck(@PathVariable UUID gameId, @RequestBody AddDeckRequest deck) {
        return gameMapper.toDto(gameService.addDeck(gameId, deck.deckId));
    }

    @PostMapping("/shuffle")
    public void shuffleDecks(@PathVariable String gameId) {

    }

    public record AddDeckRequest(UUID deckId) {

    }
}
