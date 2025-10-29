package com.interview.deckgame.deck.internal;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DeckCardEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private DeckEntity deck;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private Card.CardValue numericValue;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private Card.CardType type;

    public void setCard(Card card) {
        this.numericValue = card.value();
        this.type = card.cardType();
    }

    public Card getCard() {
        return new Card(this.numericValue, this.type);
    }
}
