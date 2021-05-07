package com.springtechnicaltest.repository;

import com.springtechnicaltest.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findByLogin(String login);
}
