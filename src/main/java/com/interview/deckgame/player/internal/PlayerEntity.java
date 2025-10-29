package com.interview.deckgame.player.internal;

import java.util.ArrayList;
import java.util.List;

import com.interview.deckgame.game.internal.DealtCardEntity;
import com.interview.deckgame.game.internal.GameEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private GameEntity game;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<DealtCardEntity> dealtCards = new ArrayList<>();
}
