package com.springtechnicaltest.service.serrviceImpl;

import com.springtechnicaltest.dto.PlayerDTO;
import com.springtechnicaltest.exception.NotFoundException;
import com.springtechnicaltest.model.Player;
import com.springtechnicaltest.repository.PlayerRepository;
import com.springtechnicaltest.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Player createPlayer(Player player) throws NotFoundException {
        if(player == null){
            throw new NotFoundException("Player not found, please check a valid player");
        }
        Player newPlayer= new Player();
        newPlayer.setLogin(player.getLogin());
        return playerRepository.save(newPlayer);
    }
    @Override
    public List<Player> listPlayers() {
        return (List<Player>) playerRepository.findAll();
    }
    @Override
    public Player getPlayer(String login) throws NotFoundException {
        if(ObjectUtils.isEmpty(login)){
            throw new NotFoundException("There is no player with given login:"+login);
        }
        return playerRepository.findByLogin(login);
    }

}
