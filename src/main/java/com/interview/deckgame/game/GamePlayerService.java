package com.interview.deckgame.game;

import java.util.List;

import com.interview.deckgame.deck.internal.CardEntity;

public interface GamePlayerService {
    List<CardEntity> getPlayerCards(Long playerId);
}
