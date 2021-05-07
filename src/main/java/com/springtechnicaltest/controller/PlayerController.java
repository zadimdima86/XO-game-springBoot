package com.springtechnicaltest.controller;

import com.springtechnicaltest.dto.PlayerDTO;
import com.springtechnicaltest.service.serrviceImpl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerService;


    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody PlayerDTO newPlayerDTO) {
        if (newPlayerDTO != null) {
            return ResponseEntity.ok(playerService.createPlayer(newPlayerDTO));
        }else {
            return ResponseEntity.badRequest().body("Bad request received");
        }

    }

    @GetMapping("/list")
    public ResponseEntity<?> getPlayers() {
        if (!CollectionUtils.isEmpty(playerService.listPlayers())){
            return ResponseEntity.ok(   playerService.listPlayers());
        }else{

            return ResponseEntity.noContent().build();
        }

    }

}
