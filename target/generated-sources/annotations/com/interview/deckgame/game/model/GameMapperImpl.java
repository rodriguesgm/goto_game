package com.interview.deckgame.game.model;

import com.interview.deckgame.game.internal.GameEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-29T18:18:38-0400",
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
}
