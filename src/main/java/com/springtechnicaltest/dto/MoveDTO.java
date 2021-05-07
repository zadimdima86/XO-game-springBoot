package com.springtechnicaltest.dto;

import com.springtechnicaltest.enums.GameStatus;
import com.springtechnicaltest.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MoveDTO {
    private int boardColumn;
    private int boardRow;
    private String login;
    private GameStatus gameStatus;
    private Piece playerPieceCode;
}
