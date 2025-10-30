package com.interview.deckgame.player;

import java.util.List;
import java.util.Optional;

import com.interview.deckgame.game.internal.GameEntity;
import com.interview.deckgame.player.internal.PlayerEntity;

public interface PlayerService {
    PlayerEntity addToGame(Long playerId, GameEntity game);

    void removeFromGame(Long playerId, GameEntity game);

    Optional<PlayerEntity> findById(Long playerId);

    List<PlayerEntity> getPlayers(Long gameId);
}
