package com.interview.deckgame.game;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.game.internal.GameService;
import com.interview.deckgame.game.model.CardDto;
import com.interview.deckgame.game.model.CardMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games/{gameId}/players")
public class GamePlayerController {

    private final GameService gameService;
    private final CardMapper cardMapper;

    @GetMapping
    public void getPlayers(@PathVariable Long gameId) {
        // TODO - get all players of the game
    }

    @PostMapping
    public void addPlayer(@PathVariable Long gameId, @RequestBody AddPlayerRequest request) {
        gameService.addPlayer(gameId, request.playerId());
    }

    @GetMapping("/{playerId}/cards")
    public void getCards(@PathVariable Long gameId, @RequestBody AddPlayerRequest request) {
        // Get the cards of a player
    }

    @GetMapping("/{playerId}/cards/deal")
    public List<CardDto> dealCard(@PathVariable Long gameId, @RequestBody AddPlayerRequest request) {
        int numOfCards = ObjectUtils.defaultIfNull(request.numberOfCards(), 1);
        return gameService.dealCards(gameId, request.playerId(), numOfCards).stream()
                .map(cardMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{playerId}")
    public void removePlayer(@PathVariable Long gameId, @PathVariable Long playerId) {
        gameService.removePlayer(gameId, playerId);
    }

    public record AddPlayerRequest(Long playerId, Integer numberOfCards) {
    }
}
