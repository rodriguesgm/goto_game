package com.interview.deckgame.player.internal;

import org.springframework.stereotype.Service;

import com.interview.deckgame.game.internal.GameEntity;
import com.interview.deckgame.player.PlayerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public void addToGame(Long playerId, GameEntity game) {
        final var player = playerRepository.findById(playerId).orElseThrow();
        player.setGame(game);
        playerRepository.save(player);
    }

    @Override
    public void removeFromGame(Long playerId, GameEntity game) {
        final var player = playerRepository.findById(playerId).orElseThrow();
        if (!player.getGame().getId().equals(game.getId())) {
            throw new IllegalStateException("Player is not part of the specified game");
        }
        player.setGame(null);
        playerRepository.save(player);
    }
}
