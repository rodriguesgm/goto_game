package com.interview.deckgame.deck.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.interview.deckgame.deck.internal.DeckEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeckMapper {

    @Mapping(target = "id")
    DeckDto toDto(DeckEntity deckEntity);
}
