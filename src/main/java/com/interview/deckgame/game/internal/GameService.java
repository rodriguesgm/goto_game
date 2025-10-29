package com.interview.deckgame.game.internal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.smartcardio.Card;

import org.springframework.stereotype.Service;

import com.interview.deckgame.deck.DeckService;
import com.interview.deckgame.deck.internal.CardEntity;
import com.interview.deckgame.player.PlayerService;
import com.interview.deckgame.player.internal.PlayerEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final DeckService deckService;
    private final PlayerService playerService;
    private final GameDeckRepository gameDeckRepository;
    private final DealtCardRepository dealtCardRepository;

    public GameEntity create(String gameName) {
        final var game = new GameEntity();
        game.setName(gameName);
        return gameRepository.save(game);
    }

    public void delete(Long gameId) {
        gameRepository.deleteById(gameId);
    }

    @Transactional
    public GameEntity addDeck(Long gameId, Long deckId) {
        var gameDeckOpt = gameDeckRepository.findByGameIdAndDeckId(gameId, deckId);
        if (gameDeckOpt.isPresent()) {
            throw new IllegalStateException("Deck is already associated with the game");
        }

        final var game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found"));
        final var deck = deckService.getDeckById(deckId).orElseThrow(() -> new IllegalArgumentException("Deck not found"));

        final var totalDecks = gameDeckRepository.countByGameId(gameId);

        final var gameDeck = new GameDeckEntity();
        gameDeck.setDeck(deck);
        gameDeck.setGame(game);
        gameDeck.setOrderAdded(totalDecks == null ? 1 : totalDecks + 1);
        gameDeckRepository.save(gameDeck);

        // Initialize undealt cards in the game
        for (CardEntity card : deck.getCards()) {
            final var dc = new DealtCardEntity();
            dc.setGame(game);
            dc.setCard(card);
            dc.setDealtAt(null);
            dc.setPlayer(null);
            dealtCardRepository.save(dc);
        }

        return game;
    }

    @Transactional
    public void shuffle(Long gameId) {
        List<DealtCardEntity> undealt = dealtCardRepository.findByGameIdAndPlayerIsNull(gameId);
        Random random = new Random();

        for (int i = undealt.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(undealt, i, j);
        }

        // Persist the shuffled order
        for (int i = 0; i < undealt.size(); i++) {
            undealt.get(i).setDealOrder(i);
        }

        dealtCardRepository.saveAll(undealt);
    }

    @Transactional
    public List<CardEntity> dealCards(Long gameId, Long playerId, int count) {
        // TODO = validate that player is part of the game
        // TODO = validate that player deck is shuffled before dealing cards

        if (count == 0 ) {
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
        // Validate = check if player is already added to a game (this own game or other)

        GameEntity game = gameRepository.findById(gameId).orElseThrow();
        playerService.addToGame(playerId, game);
    }

    public void removePlayer(Long gameId, Long playerId) {
        GameEntity game = gameRepository.findById(gameId).orElseThrow();
        playerService.removeFromGame(playerId, game);
    }

}
