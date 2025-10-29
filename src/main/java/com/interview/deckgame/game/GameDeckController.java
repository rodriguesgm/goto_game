package com.interview.deckgame.game;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.game.internal.GameDeckService;
import com.interview.deckgame.game.model.GameDto;
import com.interview.deckgame.game.model.GameMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games/{gameId}/decks")
public class GameDeckController {

    private final GameMapper gameMapper;
    private final GameDeckService gameDeckService;

    @PostMapping
    public GameDto addDeck(@PathVariable Long gameId, @RequestBody AddDeckRequest deck) {
        return gameMapper.toDto(gameDeckService.addDeck(gameId, deck.deckId));
    }

    @PostMapping("/shuffle")
    public void shuffleDecks(@PathVariable Long gameId) {
        gameDeckService.shuffle(gameId);
    }

    public record AddDeckRequest(Long deckId) {

    }
}
