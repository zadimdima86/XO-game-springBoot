package com.springtechnicaltest;

import com.springtechnicaltest.model.Player;
import com.springtechnicaltest.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PlayerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    void getAllPlayers() throws Exception {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(1L, "player1"));
        playerList.add(new Player(2L, "player2"));
        when(playerService.listPlayers()).thenReturn(playerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/player/list")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

}
