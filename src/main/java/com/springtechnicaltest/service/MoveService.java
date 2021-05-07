package com.springtechnicaltest.service;

import com.springtechnicaltest.dto.MoveDTO;
import com.springtechnicaltest.enums.GameStatus;
import com.springtechnicaltest.model.Game;
import com.springtechnicaltest.model.Move;
import com.springtechnicaltest.model.Player;
import com.springtechnicaltest.model.Position;

import java.util.List;

public interface MoveService {
    Move createMove(Game game, Player player, Position createMove);

    List<Position> getPlayerMovePositionsInGame(Game game, Player player);

    List<Position> getTakenMovePositionsInGame(Game game);

    int getTheNumberOfPlayerMovesInGame(Game game, Player player);

    GameStatus checkCurrentGameStatus(Game game);

    List<MoveDTO> getMovesInGame(Game game);

    boolean isPlayerTurn(Game game, Player firstPlayer, Player secondPlayer);
}
