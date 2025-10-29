package com.interview.deckgame.player.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.interview.deckgame.player.internal.PlayerEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerMapper {

    PlayerDto toDto(PlayerEntity playerEntity);
}
