package com.springtechnicaltest.dto;

import com.springtechnicaltest.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRequestDTO {
    @NotNull
    private String login;
    @NotNull
    private Long gameId;
}
