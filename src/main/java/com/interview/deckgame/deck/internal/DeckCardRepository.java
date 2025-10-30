package com.interview.deckgame.deck.internal;

import org.springframework.data.repository.CrudRepository;

 interface DeckCardRepository extends CrudRepository<CardEntity, Long> {
}
