package com.interview.deckgame.game;

import java.util.UUID;

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
    public void getPlayers(@PathVariable UUID gameId) {
    }


    @PostMapping
    public void addPlayer(@PathVariable UUID gameId, @RequestBody UUID player) {
        gameService.addPlayer(gameId, player);
    }

    @DeleteMapping("/{playerId}")
    public void removePlayer(@PathVariable UUID gameId, @RequestBody UUID playerId) {
        gameService.removePlayer(gameId, playerId);
    }
}
