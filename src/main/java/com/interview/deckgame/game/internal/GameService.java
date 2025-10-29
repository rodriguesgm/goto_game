package com.interview.deckgame.game.internal;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.interview.deckgame.deck.DeckService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final DeckService deckService;

    public GameEntity create() {
        final var game = new GameEntity();
        return gameRepository.save(game);
    }

    public void delete(UUID gameId) {
        gameRepository.deleteById(gameId);
    }

    public GameEntity addDeck(UUID gameId, UUID deckId) {
        final var deckGame = deckService.getDeckById(deckId);
        deckGame.ifPresentOrElse(deck -> {
            if (deck.getGame() != null && !deck.getGame().getId().equals(gameId)) {
                throw new IllegalStateException("Deck is already associated with a game");
            }
        }, () -> {
            throw new IllegalArgumentException("Deck not found");
        });

        final var game = gameRepository.findById(gameId);
        if (game.isEmpty()) {
            throw new IllegalArgumentException("Game not found");
        }

        return game.map(g -> {
            g.getDecks().add(deckGame.get());
            return gameRepository.save(g);
        }).orElseThrow(() -> new IllegalArgumentException("Game not found"));
    }

    public void addPlayer(UUID gameId, UUID playerId) {
        // Validate = check if player is already added to a game (this own game or other)


        // find game
        // save player
    }

    public void removePlayer(UUID gameId, UUID playerId) {
        // find game
        // save player
    }

}
