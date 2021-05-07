package com.springtechnicaltest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "move")
public class Move implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "board_row", nullable = false)
    private int boardRow;

    @Column(name = "board_column", nullable = false)
    private int boardColumn;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = true)
    private Player player;

}
