package com.interview.deckgame.player.internal;

import org.springframework.data.repository.CrudRepository;

 interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {
}
