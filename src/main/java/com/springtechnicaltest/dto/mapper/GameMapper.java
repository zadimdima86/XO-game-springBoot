package com.springtechnicaltest.dto.mapper;


import com.springtechnicaltest.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GameMapper extends EntityMapper<Game, GameDTO> {

}
