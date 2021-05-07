package com.springtechnicaltest.service.serrviceImpl;

import com.springtechnicaltest.dto.MoveDTO;
import com.springtechnicaltest.enums.GameStatus;
import com.springtechnicaltest.enums.Piece;
import com.springtechnicaltest.model.Game;
import com.springtechnicaltest.model.Move;
import com.springtechnicaltest.model.Player;
import com.springtechnicaltest.model.Position;
import com.springtechnicaltest.repository.MoveRepository;
import com.springtechnicaltest.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoveServiceImpl implements MoveService {
    @Autowired
    private MoveRepository moveRepository;

    @Override
    public Move createMove(Game game, Player player, Position createMove) {
        return moveRepository.save(Move.builder()
                .boardRow(createMove.getBoardRow())
                .boardColumn(createMove.getBoardColumn())
                .player(player)
                .game(game)
                .build());
    }
    @Override
    public List<Position> getPlayerMovePositionsInGame(Game game, Player player) {
        return moveRepository.findByGameAndPlayer(game, player).stream()
                .map(move -> new Position(move.getBoardRow(), move.getBoardColumn()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Position> getTakenMovePositionsInGame(Game game) {
        return moveRepository.findByGame(game).stream()
                .map(move -> new Position(move.getBoardRow(), move.getBoardColumn()))
                .collect(Collectors.toList());
    }
    @Override
    public int getTheNumberOfPlayerMovesInGame(Game game, Player player) {
        return moveRepository.countByGameAndPlayer(game, player);
    }
    @Override
    public GameStatus checkCurrentGameStatus(Game game) {
        if (GameLogicConditions.isWinner(getPlayerMovePositionsInGame(game, game.getFirstPlayer()))) {
            return GameStatus.FIRST_PLAYER_WON;
        } else if (GameLogicConditions.isWinner(getPlayerMovePositionsInGame(game, game.getSecondPlayer()))) {
            return GameStatus.SECOND_PLAYER_WON;
        } else if (GameLogicConditions.isBoardIsFull(getTakenMovePositionsInGame(game))) {
            return GameStatus.DRAW;
        } else if (GameLogicConditions.playerTurn((getTheNumberOfPlayerMovesInGame(game, game.getFirstPlayer())), (getTheNumberOfPlayerMovesInGame(game, game.getSecondPlayer())))) {
            return GameStatus.WAITS_FOR_PLAYER;
        } else {
            return GameStatus.IN_PROGRESS;
        }

    }
    @Override
    public List<MoveDTO> getMovesInGame(Game game) {

        List<Move> movesInGame = moveRepository.findByGame(game);
        List<MoveDTO> moves = new ArrayList<>();
        Piece currentPiece = game.getFirstPlayerPieceCode();
        if (!CollectionUtils.isEmpty(movesInGame)) {
            for (Move move : movesInGame) {
                moves.add(MoveDTO.builder()
                        .boardColumn(move.getBoardColumn())
                        .boardRow(move.getBoardRow())
                        .gameStatus(move.getGame() != null ? move.getGame().getGameStatus() : null)
                        .login(move.getPlayer() != null ? move.getPlayer().getLogin() : null)
                        .playerPieceCode(currentPiece)
                        .build());

                currentPiece = currentPiece == Piece.X ? Piece.O : Piece.X;
            }
        }
        return moves;
    }
    @Override
    public boolean isPlayerTurn(Game game, Player firstPlayer, Player secondPlayer) {
        return GameLogicConditions.playerTurn(getTheNumberOfPlayerMovesInGame(game, firstPlayer),
                getTheNumberOfPlayerMovesInGame(game, secondPlayer));
    }
}
