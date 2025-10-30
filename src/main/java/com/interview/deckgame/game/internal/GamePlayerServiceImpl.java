package com.interview.deckgame.game.internal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.deckgame.deck.internal.CardEntity;
import com.interview.deckgame.game.GamePlayerService;
import com.interview.deckgame.player.PlayerService;
import com.interview.deckgame.player.internal.PlayerEntity;
import com.interview.deckgame.shared.CardRank;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GamePlayerServiceImpl implements GamePlayerService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final DealtCardRepository dealtCardRepository;

    public Map<PlayerEntity, Integer> getPlayers(Long gameId) {
        final var playersInGame =
                dealtCardRepository.findByGameIdAndPlayerIsNotNull(gameId).stream().map(DealtCardEntity::getPlayer).toList();

        Map<PlayerEntity, Integer> totals = new HashMap<>();
        for (PlayerEntity player : playersInGame) {
            int sum = 0;
            for (CardEntity c : getPlayerCards(player.getId())) {
                sum += CardRank.rankFor((c.getValue()));
            }
            totals.put(player, sum);
        }

        // Sort descending by value
        return totals.entrySet().stream()
                .sorted(Map.Entry.<PlayerEntity, Integer>comparingByValue().reversed())
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

    }

    @Override
    public List<CardEntity> getPlayerCards(Long playerId) {
        return dealtCardRepository.findByPlayerId(playerId).stream().map(DealtCardEntity::getCard).toList();
    }

    @Transactional
    public List<CardEntity> dealCards(Long gameId, Long playerId, int count) {
        // TODO: goto: validate that player is part of the game
        // TODO: goto: validate that player deck is shuffled before dealing cards
        // TODO: goto: concurrency handling to avoid dealing the same card to multiple/same players, multiple requests etc.

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
        // TODO: goto: validate if player is already added to a game

        GameEntity game = gameRepository.findById(gameId).orElseThrow();
        playerService.addToGame(playerId, game);
    }

    public void removePlayer(Long gameId, Long playerId) {
        GameEntity game = gameRepository.findById(gameId).orElseThrow();
        playerService.removeFromGame(playerId, game);
    }

}
