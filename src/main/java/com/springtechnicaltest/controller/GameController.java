package com.springtechnicaltest.controller;

import com.springtechnicaltest.dto.GameRequestDTO;
import com.springtechnicaltest.dto.PlayerDTO;
import com.springtechnicaltest.dto.mapper.GameMapper;
import com.springtechnicaltest.dto.mapper.PlayerMapper;
import com.springtechnicaltest.exception.InvalidParamException;
import com.springtechnicaltest.exception.NotFoundException;
import com.springtechnicaltest.model.*;
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
    @Autowired
    GameMapper gameMapper;
    @Autowired
    PlayerMapper playerMapper;

    /**
     * @param playerDTO
     * @return Game created for the first time with a  first player
     */
    @PostMapping("/create")
    public ResponseEntity<?> createNewGame(@RequestBody PlayerDTO playerDTO) throws NotFoundException {
        log.info("create  game request:{}", playerDTO);
        if (playerDTO == null) {
            return ResponseEntity.badRequest().body("Bad request received with player:"+playerDTO);
        }
        return ResponseEntity.ok(gameService.createGame(playerMapper.dtoToEntity(playerDTO)));
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinGame(@RequestBody GameRequestDTO gameRequest) throws NotFoundException, InvalidParamException {
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
    public ResponseEntity<?> getGameProperties(@PathVariable Long id) throws NotFoundException {
        log.info("Game request by ID:{} ", id);
        if (id != null) {
            return ResponseEntity.ok(gameService.getGame(id));
        } else
            return ResponseEntity.badRequest().body("Bad request received: GAme not found");
    }

}
