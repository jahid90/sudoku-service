package org.jahiduls.sudokuservice.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Row {

    private final List<Cell> cells;

    public Row() {
        this.cells = new ArrayList<>(Puzzle.SIZE);
    }

    public void addCell(Cell cell) {

        if (cells.size() > Puzzle.SIZE) {
            throw new RuntimeException("Added too many cells!");
        }

        cells.add(cell);
    }

    @Override
    public String toString() {
        return Arrays.toString(cells.toArray());
    }
}
