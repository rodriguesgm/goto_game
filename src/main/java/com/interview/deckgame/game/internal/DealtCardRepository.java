package com.interview.deckgame.game.internal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DealtCardRepository extends CrudRepository<DealtCardEntity, Long> {
    List<DealtCardEntity> findByGameIdAndPlayerIsNull(Long gameId);

    List<DealtCardEntity> findByGameIdAndPlayerIsNullOrderByDealOrderAsc(Long gameId);
}
