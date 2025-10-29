package com.interview.deckgame.deck.internal;

import org.springframework.data.repository.CrudRepository;

public interface DeckCardRepository extends CrudRepository<CardEntity, Long> {
}
