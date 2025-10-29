package com.interview.deckgame.player.internal;

import java.util.UUID;

import com.interview.deckgame.game.internal.GameEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PlayerEntity {

    @GeneratedValue
    @Id
    private UUID id;

    private String name;

    @ManyToOne
    private GameEntity game;
}
