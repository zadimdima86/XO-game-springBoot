package com.springtechnicaltest.dto.mapper;

import com.springtechnicaltest.dto.MoveDTO;
import com.springtechnicaltest.model.Move;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MoveMapper extends EntityMapper<Move, MoveDTO> {
}
