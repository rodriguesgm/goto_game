package com.interview.deckgame.game.model;

import com.interview.deckgame.deck.internal.CardEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-29T19:35:04-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDto toDto(CardEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String suit = null;
        String value = null;

        id = entity.getId();
        if ( entity.getSuit() != null ) {
            suit = entity.getSuit().name();
        }
        if ( entity.getValue() != null ) {
            value = entity.getValue().name();
        }

        CardDto cardDto = new CardDto( id, suit, value );

        return cardDto;
    }
}
