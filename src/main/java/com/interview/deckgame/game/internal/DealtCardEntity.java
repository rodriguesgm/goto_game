package com.interview.deckgame.game.internal;

import java.time.LocalDateTime;

import com.interview.deckgame.deck.internal.CardEntity;
import com.interview.deckgame.player.internal.PlayerEntity;

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
public class DealtCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GameEntity game;
    @ManyToOne
    private CardEntity card;
    @ManyToOne
    private PlayerEntity player; // null = undealt

    private int dealOrder;
    private LocalDateTime dealtAt;
}
