package com.interview.deckgame.game;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.deck.internal.CardEntity;
import com.interview.deckgame.game.internal.GameDeckService;
import com.interview.deckgame.game.model.GameDto;
import com.interview.deckgame.game.model.GameMapper;
import com.interview.deckgame.security.ValidateApplicationToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games/{gameId}/decks")
public class GameDeckController {

    private final GameMapper gameMapper;
    private final GameDeckService gameDeckService;

    @ValidateApplicationToken
    @PostMapping
    public GameDto addDeck(@PathVariable Long gameId, @RequestBody AddDeckRequest deck) {
        return gameMapper.toDto(gameDeckService.addDeck(gameId, deck.deckId));
    }

    @ValidateApplicationToken
    @PostMapping("/shuffle")
    public void shuffleDecks(@PathVariable Long gameId) {
        gameDeckService.shuffle(gameId);
    }

    // TODO = irc, the rest endpoint path in here is kind of weird, not really Rest Standards compliant
    @ValidateApplicationToken
    @GetMapping("/cards/suit-counts")
    public Map<CardEntity.Suit, Long> suitCounts(@PathVariable Long gameId) {
        return gameDeckService.countRemainingBySuit(gameId);
    }

    public record AddDeckRequest(Long deckId) {

    }
}
