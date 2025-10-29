package com.interview.deckgame.player.model;

import com.interview.deckgame.player.internal.PlayerEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-29T19:06:11-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PlayerDto toDto(PlayerEntity playerEntity) {
        if ( playerEntity == null ) {
            return null;
        }

        String id = null;

        if ( playerEntity.getId() != null ) {
            id = String.valueOf( playerEntity.getId() );
        }

        PlayerDto playerDto = new PlayerDto( id );

        return playerDto;
    }
}
