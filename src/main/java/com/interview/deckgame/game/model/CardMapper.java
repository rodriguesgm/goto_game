package com.interview.deckgame.game.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.interview.deckgame.deck.internal.CardEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {

    CardDto toDto(CardEntity entity);
}
