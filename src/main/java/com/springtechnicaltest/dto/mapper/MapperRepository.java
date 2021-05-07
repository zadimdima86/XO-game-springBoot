package com.springtechnicaltest.dto.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class MapperRepository {
    @Bean
    public PlayerMapper getPlayerMapper() {
        return Mappers.getMapper(PlayerMapper.class);
    }
    @Bean
    public GameMapper getGameMapper() {
        return Mappers.getMapper(GameMapper.class);
    }
    @Bean
    public MoveMapper getMoveMapper() {
        return Mappers.getMapper(MoveMapper.class);
    }
}
