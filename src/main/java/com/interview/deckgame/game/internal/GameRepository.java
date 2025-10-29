package com.interview.deckgame.game.internal;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository  extends CrudRepository<GameEntity, UUID> {
}
