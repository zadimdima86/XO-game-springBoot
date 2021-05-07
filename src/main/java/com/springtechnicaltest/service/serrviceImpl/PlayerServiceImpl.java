package com.springtechnicaltest.service.serrviceImpl;

import com.springtechnicaltest.dto.PlayerDTO;
import com.springtechnicaltest.model.Player;
import com.springtechnicaltest.repository.PlayerRepository;
import com.springtechnicaltest.service.PlayerService;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuperBuilder
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Player createPlayer(PlayerDTO playerDTO) {
        Player player= new Player();
        player.setLogin(playerDTO.getLogin());
        return playerRepository.save(player);
    /*  return playerRepository.save(Player.builder()
                                              .login(playerDTO.getLogin())
                                              .build());*/
    }
    @Override
    public List<Player> listPlayers() {
        return (List<Player>) playerRepository.findAll();
    }
    @Override
    public Player getPlayer(String login){
        return playerRepository.findByLogin(login);
    }

}
