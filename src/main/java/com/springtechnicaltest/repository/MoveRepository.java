package com.springtechnicaltest.repository;

import com.springtechnicaltest.model.Game;
import com.springtechnicaltest.model.Move;
import com.springtechnicaltest.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends CrudRepository<Move, Long> {

    List<Move> findByGame(Game game);
    List<Move> findByGameAndPlayer(Game game, Player player);
    int countByGameAndPlayer(Game game, Player player);
}
