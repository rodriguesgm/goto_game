package com.interview.deckgame.game.internal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    public Iterable<GameEntity> list() {
        return gameRepository.findAll();
    }

    public GameEntity create(String gameName) {
        final var game = new GameEntity();
        game.setName(gameName);
        return gameRepository.save(game);
    }

    public void delete(Long gameId) {
        gameRepository.deleteById(gameId);
    }

}
