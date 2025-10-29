package com.interview.deckgame.game.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.interview.deckgame.deck.internal.DeckEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class GameEntity {
    @GeneratedValue
    @Id
    private UUID id;

    @OneToMany
    private List<DeckEntity> decks = new ArrayList<>();
}
