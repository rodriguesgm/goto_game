package com.interview.deckgame.deck.internal;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<DeckEntity, UUID> {
}
