package com.interview.deckgame.game;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.game.internal.GameService;
import com.interview.deckgame.game.model.GameDto;
import com.interview.deckgame.game.model.GameMapper;
import com.interview.deckgame.security.ValidateApplicationToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final GameMapper gameMapper;

    @ValidateApplicationToken
    @PostMapping
    public GameDto create(@RequestBody GameCreateRequest request) {
        return gameMapper.toDto(gameService.create(request.name));
    }

    @ValidateApplicationToken
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> delete(@PathVariable Long gameId) {
        gameService.delete(gameId);
        return ResponseEntity.noContent().build();
    }

    public record GameCreateRequest(String name) {
    }
}
