package com.interview.deckgame.player;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.game.GamePlayerService;
import com.interview.deckgame.game.model.CardDto;
import com.interview.deckgame.game.model.CardMapper;
import com.interview.deckgame.player.internal.PlayerServiceImpl;
import com.interview.deckgame.player.model.PlayerDto;
import com.interview.deckgame.player.model.PlayerMapper;
import com.interview.deckgame.security.ValidateApplicationToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerMapper playerMapper;
    private final PlayerServiceImpl playerService;
    private final GamePlayerService gamePlayerService;
    private final CardMapper cardMapper;

    @PostMapping
    @ValidateApplicationToken
    public PlayerDto create(@RequestBody PlayerCreateRequest request) {
        return playerMapper.toDto(playerService.create(request.name));
    }

    @GetMapping("/{playerId}/cards")
    @ValidateApplicationToken
    public List<CardDto> getPlayerCards(@PathVariable Long playerId) {
        return gamePlayerService.getPlayerCards(playerId).stream()
                .map(cardMapper::toDto)
                .toList();
    }

    public record PlayerCreateRequest(String name) {
    }
}
