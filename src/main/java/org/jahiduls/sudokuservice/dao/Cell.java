package org.jahiduls.sudokuservice.dao;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Cell {

    @Getter
    private final int value;

    @Override
    public String toString() {
        return "" + value;
    }
}
