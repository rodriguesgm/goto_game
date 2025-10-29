package com.interview.deckgame.game.internal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.interview.deckgame.deck.internal.CardEntity;
import com.interview.deckgame.player.PlayerService;
import com.interview.deckgame.player.internal.PlayerEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GamePlayerService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final DealtCardRepository dealtCardRepository;

    @Transactional
    public List<CardEntity> dealCards(Long gameId, Long playerId, int count) {
        // TODO = validate that player is part of the game
        // TODO = validate that player deck is shuffled before dealing cards

        if (count == 0) {
            return Collections.emptyList();
        }

        List<DealtCardEntity> undealt = dealtCardRepository.findByGameIdAndPlayerIsNullOrderByDealOrderAsc(gameId);
        PlayerEntity player = playerService.findById(playerId).orElseThrow();

        // I think we could put this limit in the query itself, but for simplicity doing it here
        List<DealtCardEntity> toDeal = undealt.stream().limit(count).toList();
        List<CardEntity> cards = new ArrayList<>();
        for (DealtCardEntity dc : toDeal) {
            dc.setPlayer(player);
            dc.setDealtAt(LocalDateTime.now());
            cards.add(dc.getCard());
        }

        dealtCardRepository.saveAll(toDeal);
        return cards;
    }

    public void addPlayer(Long gameId, Long playerId) {
        // TODO = validate if player is already added to a game

        GameEntity game = gameRepository.findById(gameId).orElseThrow();
        playerService.addToGame(playerId, game);
    }

    public void removePlayer(Long gameId, Long playerId) {
        GameEntity game = gameRepository.findById(gameId).orElseThrow();
        playerService.removeFromGame(playerId, game);
    }

}
