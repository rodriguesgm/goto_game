package com.interview.deckgame.player;

import java.util.Optional;

import com.interview.deckgame.game.internal.GameEntity;
import com.interview.deckgame.player.internal.PlayerEntity;

public interface PlayerService {
    void addToGame(Long playerId, GameEntity game);

    void removeFromGame(Long playerId, GameEntity game);

    Optional<PlayerEntity> findById(Long playerId);
}
