package com.springtechnicaltest.service;

import com.springtechnicaltest.dto.PlayerDTO;
import com.springtechnicaltest.exception.NotFoundException;
import com.springtechnicaltest.model.Player;

import java.util.List;

public interface PlayerService {
    Player createPlayer(Player player) throws NotFoundException;

    List<Player> listPlayers();

    Player getPlayer(String login) throws NotFoundException;
}
