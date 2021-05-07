package com.springtechnicaltest.service;

import com.springtechnicaltest.dto.PlayerDTO;
import com.springtechnicaltest.model.Player;

import java.util.List;

public interface PlayerService {
    Player createPlayer(PlayerDTO playerDTO);

    List<Player> listPlayers();

    Player getPlayer(String login);
}
