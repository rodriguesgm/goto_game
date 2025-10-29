package com.interview.deckgame.deck;

import java.util.Optional;

import com.interview.deckgame.deck.internal.DeckEntity;

public interface DeckService {

    Optional<DeckEntity> getDeckById(Long deckId);
}
