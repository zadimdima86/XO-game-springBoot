package com.springtechnicaltest.dto;

import com.springtechnicaltest.model.Position;
import lombok.*;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMoveDTO {
    @NotNull
    private String login;
    @NotNull
    private Long gameId;
   private Position position;

}
