package com.interview.deckgame.player;

import com.interview.deckgame.game.internal.GameEntity;

public interface PlayerService {
    void addToGame(Long playerId, GameEntity game);

    void removeFromGame(Long playerId, GameEntity game);
}
