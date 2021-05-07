package com.springtechnicaltest.service;

import com.springtechnicaltest.enums.GameStatus;
import com.springtechnicaltest.exception.NotFoundException;
import com.springtechnicaltest.model.Game;
import com.springtechnicaltest.model.Player;

import java.util.Optional;

public interface GameService  {
    Game createGame(Player player);

    Optional<Game> getGame(Long id);

    Game updateGameStatus(Game game, GameStatus gameStatus) throws NotFoundException;

    Game joinGame(String login, Long gameId) throws NotFoundException;
}
