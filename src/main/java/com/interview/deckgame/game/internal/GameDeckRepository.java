package com.interview.deckgame.game.internal;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

interface GameDeckRepository extends CrudRepository<GameDeckEntity, Long> {
    Optional<GameDeckEntity> findByGameIdAndDeckId(Long gameId, long deckId);

    Integer countByGameId(Long gameId);
}
