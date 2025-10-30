package com.interview.deckgame.shared;

import com.interview.deckgame.deck.internal.CardEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class CardRank {

    public static int rankFor(CardEntity.Value v) {
        return switch (v) {
            case A -> 1;
            case JACK -> 11;
            case QUEEN -> 12;
            case KING -> 13;
            default -> v.ordinal() + 1;
        };
    }

}
