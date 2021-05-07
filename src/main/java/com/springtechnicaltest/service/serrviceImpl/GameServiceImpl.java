package com.springtechnicaltest.service.serrviceImpl;

import com.springtechnicaltest.enums.*;
import com.springtechnicaltest.model.*;
import com.springtechnicaltest.repository.*;
import com.springtechnicaltest.exception.NotFoundException;
import com.springtechnicaltest.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Game createGame(Player player) {
        return gameRepository.save(Game.builder()
                .firstPlayer(player)
                .firstPlayerPieceCode(Piece.X)
                .gameStatus(GameStatus.WAITS_FOR_PLAYER)
                .build());
    }

    @Override
    public Optional<Game> getGame(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public Game updateGameStatus(Game game, GameStatus gameStatus) throws NotFoundException {
        Game givenGame = getGame(game.getId()).orElseThrow(() -> new NotFoundException("Game not found"));
        givenGame.setGameStatus(gameStatus);
        return givenGame;

    }

    @Override
    public Game joinGame(String login, Long gameId) throws NotFoundException {
        Game givenGame = getGame(gameId).orElseThrow(() -> new NotFoundException("Game not found"));
        Player player2 = playerRepository.findByLogin(login);
        if (!player2.equals(givenGame.getFirstPlayer())) {
            givenGame.setSecondPlayer(player2);
            gameRepository.save(givenGame);
            updateGameStatus(givenGame, GameStatus.IN_PROGRESS);
            return givenGame;
        } else return null;


    }

}
