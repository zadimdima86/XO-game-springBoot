package com.springtechnicaltest.dto.mapper;

import com.springtechnicaltest.dto.PlayerDTO;
import com.springtechnicaltest.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PlayerMapper extends EntityMapper<Player, PlayerDTO> {
}
