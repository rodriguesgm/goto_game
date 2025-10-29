package com.interview.deckgame.game;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.game.internal.GameService;
import com.interview.deckgame.game.model.GameDto;
import com.interview.deckgame.game.model.GameMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final GameMapper gameMapper;

    @PostMapping
    public GameDto create() {
        return gameMapper.toDto(gameService.create());
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> delete(@PathVariable UUID gameId) {
        gameService.delete(gameId);
        return ResponseEntity.noContent().build();
    }

}
