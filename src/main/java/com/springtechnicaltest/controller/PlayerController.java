package com.springtechnicaltest.controller;

import com.springtechnicaltest.dto.PlayerDTO;
import com.springtechnicaltest.dto.mapper.PlayerMapper;
import com.springtechnicaltest.exception.NotFoundException;
import com.springtechnicaltest.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@Slf4j
public class PlayerController {

    @Autowired
    PlayerService playerService;
    @Autowired
    PlayerMapper playerMapper;



    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody PlayerDTO newPlayerDTO) throws NotFoundException {
        log.info("Create account player request received: {}",newPlayerDTO );
        if (newPlayerDTO == null) {
            return ResponseEntity.badRequest().body("Bad request received");
        }
        return ResponseEntity.ok(playerService.createPlayer(playerMapper.dtoToEntity(newPlayerDTO)));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getPlayers() {
        log.info("get players list request received" );
        if (!CollectionUtils.isEmpty(playerService.listPlayers())){
            return ResponseEntity.ok(playerService.listPlayers());
        }else{
            return ResponseEntity.noContent().build();
        }

    }

}
