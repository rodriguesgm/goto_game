package com.interview.deckgame.player.internal;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.interview.deckgame.game.internal.GameEntity;
import com.interview.deckgame.player.PlayerService;
import com.interview.deckgame.shared.InvalidOperationException;

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
            throw new InvalidOperationException("Player %s is not part of the game %s".formatted(playerId, game.getId()));
        }
        player.setGame(null);
        playerRepository.save(player);
    }

    @Override
    public Optional<PlayerEntity> findById(Long playerId) {
        return playerRepository.findById(playerId);
    }

    public PlayerEntity create(String name) {
        final var player = new PlayerEntity();
        player.setName(name);
        return playerRepository.save(player);
    }
}
