package com.springtechnicaltest.service.serrviceImpl;

import com.springtechnicaltest.enums.*;
import com.springtechnicaltest.exception.InvalidParamException;
import com.springtechnicaltest.model.*;
import com.springtechnicaltest.repository.*;
import com.springtechnicaltest.exception.NotFoundException;
import com.springtechnicaltest.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Game createGame(Player player) throws NotFoundException {
        if (player == null) {
            throw new NotFoundException("Player not found, please check a valid player");
        }
        return gameRepository.save(Game.builder()
                .firstPlayer(player)
                .firstPlayerPieceCode(Piece.X)
                .gameStatus(GameStatus.WAITS_FOR_PLAYER)
                .build());
    }

    @Override
    public Optional<Game> getGame(Long id) throws NotFoundException {
        if(id==null){
            throw new NotFoundException("There is no game with ID:" + id);
        }else
        return gameRepository.findById(id);
    }

    @Override
    public Game updateGameStatus(Game game, GameStatus gameStatus) throws NotFoundException {
        if(game == null || ObjectUtils.isEmpty(gameStatus)){
            throw new NotFoundException("please check the right game or game status");
        }else{
            Game givenGame = getGame(game.getId()).orElseThrow(() -> new NotFoundException("Game not found"));
            givenGame.setGameStatus(gameStatus);
            return givenGame;
        }
    }

    @Override
    public Game joinGame(String login, Long gameId) throws NotFoundException, InvalidParamException {

        if (gameId == null || ObjectUtils.isEmpty(login)) {
            throw new NotFoundException("There is no player with given login:" + login + " game with ID:" + gameId);
        }else{
            Game givenGame = getGame(gameId).orElseThrow(() -> new NotFoundException("Game  with ID:" + gameId + "not found"));
            Player player2 = playerRepository.findByLogin(login);
            if (!player2.equals(givenGame.getFirstPlayer())) {
                givenGame.setSecondPlayer(player2);
                gameRepository.save(givenGame);
                updateGameStatus(givenGame, GameStatus.IN_PROGRESS);
                return givenGame;
            } else throw  new InvalidParamException("This player is already playind, check another player");
        }



    }

}
