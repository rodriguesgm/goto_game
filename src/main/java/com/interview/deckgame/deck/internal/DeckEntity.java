package com.interview.deckgame.deck.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.interview.deckgame.game.internal.GameEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DeckEntity {

    @GeneratedValue
    @Id
    private UUID id;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private List<DeckCardEntity> cards = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private GameEntity game;
}
