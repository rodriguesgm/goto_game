package com.interview.deckgame.game.model;

import com.interview.deckgame.game.internal.GameEntity;
import com.interview.deckgame.player.internal.PlayerEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-29T20:12:30-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public GameDto toDto(GameEntity gameEntity) {
        if ( gameEntity == null ) {
            return null;
        }

        String id = null;

        if ( gameEntity.getId() != null ) {
            id = String.valueOf( gameEntity.getId() );
        }

        GameDto gameDto = new GameDto( id );

        return gameDto;
    }

    @Override
    public PlayerScoreDto toPlayerScoreDto(PlayerEntity player, Integer score) {
        if ( player == null && score == null ) {
            return null;
        }

        int score1 = 0;
        if ( score != null ) {
            score1 = score;
        }

        String playerName = null;

        PlayerScoreDto playerScoreDto = new PlayerScoreDto( playerName, score1 );

        return playerScoreDto;
    }
}
