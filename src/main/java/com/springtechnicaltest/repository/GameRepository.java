package com.springtechnicaltest.repository;

import com.springtechnicaltest.enums.GameStatus;
import com.springtechnicaltest.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByGameStatus(GameStatus gameStatus);
}
