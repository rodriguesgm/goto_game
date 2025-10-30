package com.interview.deckgame.deck.internal;

import org.springframework.data.repository.CrudRepository;

interface CardRepository extends CrudRepository<CardEntity, Long> {
}
