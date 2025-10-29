package com.interview.deckgame.deck;

import java.util.Optional;
import java.util.UUID;

import com.interview.deckgame.deck.internal.DeckEntity;

public interface DeckService {

    Optional<DeckEntity> getDeckById(UUID deckId);
}
