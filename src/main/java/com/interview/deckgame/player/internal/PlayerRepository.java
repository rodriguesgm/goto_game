package com.interview.deckgame.player.internal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

 interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {

     List<PlayerEntity> findByGameId(Long gameId);
}
