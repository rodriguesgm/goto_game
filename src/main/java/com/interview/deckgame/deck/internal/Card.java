package com.interview.deckgame.deck.internal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public record Card(CardValue value, CardType cardType) {

    @RequiredArgsConstructor
    public enum CardValue {
        ACE("Ace", 1),
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 5),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("Jack", 11),
        QUEEN("Queen", 12),
        KING("King", 13);

        @Getter
        private final String textValue;
        @Getter
        private final int numericValue;
    }

    public enum CardType {
        HEART,
        SPADE,
        CLUB,
        DIAMOND;
    }
}
