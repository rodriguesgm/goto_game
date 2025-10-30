package com.interview.deckgame.game.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.interview.deckgame.game.internal.GameEntity;
import com.interview.deckgame.player.internal.PlayerEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {

    GameDto toDto(GameEntity gameEntity);

    @Mapping(target = "playerName", source = "player.name")
    @Mapping(target = "playerId", source = "player.id")
    PlayerScoreDto toPlayerScoreDto(PlayerEntity player, Integer score);
}
