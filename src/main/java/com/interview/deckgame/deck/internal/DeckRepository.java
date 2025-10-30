package com.interview.deckgame.deck.internal;

import org.springframework.data.repository.CrudRepository;

interface DeckRepository extends CrudRepository<DeckEntity, Long> {
}
