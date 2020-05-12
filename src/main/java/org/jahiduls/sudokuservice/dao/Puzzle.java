package org.jahiduls.sudokuservice.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a sudoku puzzle.
 *
 * The puzzle consists of `SIZE` rows of `SIZE` cells each.
 * Each `BLOCK_SIZE` x `BLOCK_SIZE` segments form a block.
 */
public class Puzzle {

    public static final int SIZE = 9;
    public static final int BLOCK_SIZE = 3;

    private final List<Row> rows;

    public Puzzle() {
        this.rows = new ArrayList<>(SIZE);
    }

    public void addRow(Row row) {

        if (rows.size() > SIZE) {
            throw new RuntimeException("Added too many rows!");
        }

        rows.add(row);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        rows.forEach(row -> builder.append(row).append("\n"));

        return builder.toString();
    }
}
