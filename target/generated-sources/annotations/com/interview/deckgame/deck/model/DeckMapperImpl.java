package com.interview.deckgame.deck.model;

import com.interview.deckgame.deck.internal.DeckEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-29T16:53:52-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class DeckMapperImpl implements DeckMapper {

    @Override
    public DeckDto toDto(DeckEntity deckEntity) {
        if ( deckEntity == null ) {
            return null;
        }

        String id = null;

        if ( deckEntity.getId() != null ) {
            id = deckEntity.getId().toString();
        }

        DeckDto deckDto = new DeckDto( id );

        return deckDto;
    }
}
