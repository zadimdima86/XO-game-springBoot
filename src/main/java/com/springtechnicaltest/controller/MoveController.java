package com.springtechnicaltest.controller;

import com.springtechnicaltest.dto.CreateMoveDTO;
import com.springtechnicaltest.exception.NotFoundException;
import com.springtechnicaltest.model.Game;
import com.springtechnicaltest.model.Move;
import com.springtechnicaltest.model.Player;
import com.springtechnicaltest.service.serrviceImpl.GameServiceImpl;
import com.springtechnicaltest.service.serrviceImpl.MoveServiceImpl;
import com.springtechnicaltest.service.serrviceImpl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/move")
public class MoveController {

    @Autowired
    private MoveServiceImpl moveService;

    @Autowired
    private PlayerServiceImpl playerService;

    @Autowired
    private GameServiceImpl gameService;

    @PostMapping("/create")
    public ResponseEntity<?> createMove(@RequestBody CreateMoveDTO moveRequest) throws NotFoundException {


        if (moveRequest.getGameId() != null && !moveRequest.getLogin().isEmpty()) {
            Game givenGame = gameService.getGame(moveRequest.getGameId()).orElseThrow(() -> new NotFoundException("the given game not found"));
            Player givenPlayer = playerService.getPlayer(moveRequest.getLogin());
            Move move = moveService.createMove(givenGame, givenPlayer, moveRequest.getPosition());

            gameService.updateGameStatus(givenGame, moveService.checkCurrentGameStatus(givenGame));
            return ResponseEntity.ok(move);
        } else {
            return ResponseEntity.badRequest().body("Bad request received: Game or player not found");
        }


    }
    @GetMapping("/list/{gameId}")
    public ResponseEntity<?> getMovesInGame(@PathVariable Long  gameId) throws NotFoundException {
        if(gameId !=null){
            Game givenGame = gameService.getGame(gameId).orElseThrow(() -> new NotFoundException("the given game not found"));
            return ResponseEntity.ok(moveService.getMovesInGame(givenGame)) ;
        }else {
            return ResponseEntity.badRequest().body("Bad request received: Game with "+ gameId +" not found");
        }
    }

   /* @PostMapping("/check")
    public ResponseEntity<?> validateMoves(@RequestBody Long gameId) throws NotFoundException  {
        if(gameId !=null){
            Game givenGame = gameService.getGame(gameId).orElseThrow(() -> new NotFoundException("the given game not found"));
            return ResponseEntity.ok( moveService.getPlayerMovePositionsInGame(givenGame, playerService.getLoggedUser());
    }
    }*/

        @GetMapping("/turn/{gameId}")
    public  ResponseEntity<?>  isPlayerTurn(@PathVariable Long  gameId) throws NotFoundException  {
            if(gameId !=null) {
                Game givenGame = gameService.getGame(gameId).orElseThrow(() -> new NotFoundException("the given game not found"));
                return ResponseEntity.ok(moveService.isPlayerTurn(givenGame, givenGame.getFirstPlayer(),
                        givenGame.getSecondPlayer())) ;
            }
            else {
                return ResponseEntity.badRequest().body("Bad request received: Game with "+ gameId +" not found");
            }

    }

}
