package com.interview.deckgame.game.internal;

import org.springframework.stereotype.Service;

import com.interview.deckgame.deck.DeckService;
import com.interview.deckgame.deck.internal.CardEntity;
import com.interview.deckgame.player.PlayerService;

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
