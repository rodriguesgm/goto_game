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

import com.interview.deckgame.game.internal.GamePlayerServiceImpl;
import com.interview.deckgame.game.model.CardDto;
import com.interview.deckgame.game.model.CardMapper;
import com.interview.deckgame.game.model.GameMapper;
import com.interview.deckgame.game.model.PlayerScoreDto;
import com.interview.deckgame.security.ValidateApplicationToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games/{gameId}/players")
public class GamePlayerController {

    private final GamePlayerServiceImpl gamePlayerService;
    private final CardMapper cardMapper;
    private final GameMapper gameMapper;

    @GetMapping
    @ValidateApplicationToken
    public List<PlayerScoreDto> getPlayers(@PathVariable Long gameId) {
        return gamePlayerService.getPlayers(gameId).entrySet()
                .stream()
                .map(entry -> gameMapper.toPlayerScoreDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    @PostMapping
    @ValidateApplicationToken
    public void addPlayer(@PathVariable Long gameId, @RequestBody AddPlayerRequest request) {
        gamePlayerService.addPlayer(gameId, request.playerId());
    }

    @GetMapping("/{playerId}/cards/deal")
    @ValidateApplicationToken
    public List<CardDto> dealCards(@PathVariable Long gameId, @PathVariable Long playerId, @RequestBody DealCardsRequest request) {
        int numOfCards = ObjectUtils.defaultIfNull(request.numberOfCards(), 1);
        return gamePlayerService.dealCards(gameId, playerId, numOfCards).stream()
                .map(cardMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{playerId}")
    @ValidateApplicationToken
    public void removePlayer(@PathVariable Long gameId, @PathVariable Long playerId) {
        gamePlayerService.removePlayer(gameId, playerId);
    }

    public record AddPlayerRequest(Long playerId) {
    }

    public record DealCardsRequest(Integer numberOfCards) {
    }
}
