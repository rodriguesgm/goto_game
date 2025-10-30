package com.interview.deckgame.game.internal;

import org.springframework.data.repository.CrudRepository;

 interface GameRepository extends CrudRepository<GameEntity, Long> {
}
