package com.interview.deckgame.game.internal;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.interview.deckgame.deck.internal.CardEntity;
import com.interview.deckgame.deck.internal.DeckEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GameDeckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToStringExclude
    private GameEntity game;

    @ManyToOne
    @ToStringExclude
    private DeckEntity deck;

    private int orderAdded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof GameDeckEntity other))
            return false;

        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
