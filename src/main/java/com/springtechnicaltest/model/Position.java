package com.springtechnicaltest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Position {
    @NotNull
    int boardRow;
    @NotNull
    int boardColumn;
}
