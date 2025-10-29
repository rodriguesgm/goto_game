package com.interview.deckgame.player.internal;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {
}
