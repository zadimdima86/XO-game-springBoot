package com.springtechnicaltest.controller;

import com.springtechnicaltest.dto.GameRequestDTO;
import com.springtechnicaltest.exception.NotFoundException;
import com.springtechnicaltest.model.Game;
import com.springtechnicaltest.model.Player;
import com.springtechnicaltest.service.serrviceImpl.GameServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {
    @Autowired
    GameServiceImpl gameService;

    /**
     * @param player
     * @return Game created for y=the first time with a  first player
     */
    @PostMapping("/create")
    public ResponseEntity<Game> createNewGame(@RequestBody Player player) {
        log.info("create  game request:{}", player);
        return ResponseEntity.ok(gameService.createGame(player));
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinGame(@RequestBody GameRequestDTO gameRequest) throws NotFoundException {
        log.info("join  game request:{} {}", gameRequest.getGameId(), gameRequest.getLogin());
        if (gameRequest.getGameId() != null && gameRequest.getLogin() != null) {
            if(gameService.joinGame(gameRequest.getLogin(), gameRequest.getGameId())!= null){
                return ResponseEntity.ok(gameService.joinGame(gameRequest.getLogin(), gameRequest.getGameId()));
            }else{
                return ResponseEntity.badRequest().body("Bad request received:  player "+gameRequest.getLogin()+ "already exist");
            }

        } else {
            return ResponseEntity.badRequest().body("Bad request received: GameId:"+ gameRequest.getGameId()+"or player  not found");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameProperties(@PathVariable Long id) {
        log.info("Game request by ID:{} ", id);
        if (id != null) {
            return ResponseEntity.ok(gameService.getGame(id));
        } else
            return ResponseEntity.badRequest().body("Bad request received: GAme not found");
    }

}
