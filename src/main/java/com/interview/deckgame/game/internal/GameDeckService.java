package com.interview.deckgame.game.internal;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.interview.deckgame.deck.DeckService;
import com.interview.deckgame.deck.internal.CardEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameDeckService {

    private static final Random random = new Random();
    private final GameRepository gameRepository;
    private final DeckService deckService;
    private final GameDeckRepository gameDeckRepository;
    private final DealtCardRepository dealtCardRepository;

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
        for (int currentIndex = undealt.size() - 1; currentIndex > 0; currentIndex--) {
            int positionToSwap = random.nextInt(currentIndex + 1);

            final var temp = undealt.get(positionToSwap);
            undealt.set(positionToSwap, undealt.get(currentIndex));
            undealt.set(currentIndex, temp);
        }

        // Persist the shuffled order
        for (int i = 0; i < undealt.size(); i++) {
            undealt.get(i).setDealOrder(i);
        }

        dealtCardRepository.saveAll(undealt);
    }

}
