package com.interview.deckgame.game.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.interview.deckgame.game.internal.GameEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {

    @Mapping(target = "id")
    GameDto toDto(GameEntity gameEntity);
}
