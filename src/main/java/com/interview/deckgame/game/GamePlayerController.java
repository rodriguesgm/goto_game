package com.interview.deckgame.game;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.game.internal.GameService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games/{gameId}/players")
public class GamePlayerController {

    private final GameService gameService;

    @GetMapping
    public void getPlayers(@PathVariable Long gameId) {
        // TODO - get all players of the game
    }


    @PostMapping
    public void addPlayer(@PathVariable Long gameId, @RequestBody AddPlayerRequest request) {
        gameService.addPlayer(gameId, request.playerId());
    }

    @DeleteMapping("/{playerId}")
    public void removePlayer(@PathVariable Long gameId, @PathVariable Long playerId) {
        gameService.removePlayer(gameId, playerId);
    }

    public record AddPlayerRequest(Long playerId) {
    }
}
