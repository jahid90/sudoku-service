package org.jahiduls.sudokuservice.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleBlockSize;
import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleSize;
import static org.jahiduls.sudokuservice.utilities.PuzzleIteratorUtils.xyToBlockCellIndex;
import static org.jahiduls.sudokuservice.utilities.PuzzleIteratorUtils.xyToBlockIndex;
import static org.jahiduls.sudokuservice.utilities.PuzzleIteratorUtils.xyToColumnCellIndex;
import static org.jahiduls.sudokuservice.utilities.PuzzleIteratorUtils.xyToColumnIndex;
import static org.jahiduls.sudokuservice.utilities.PuzzleIteratorUtils.xyToRowCellIndex;
import static org.jahiduls.sudokuservice.utilities.PuzzleIteratorUtils.xyToRowIndex;

/**
 * A class representing a sudoku puzzle.
 *
 * The puzzle consists of `SIZE` rows of `SIZE` cells each.
 * Each `BLOCK_SIZE` x `BLOCK_SIZE` segments form a block.
 *
 * Each cell of the puzzle can either be empty (value 0) or have the values [1-9].
 *
 * Each cell can be indexed by x and y, where 0 <= x <= 8 && 0 <= y <= 8; x and y represent the axes.
 * bi represents the i-th block.
 *
 *    x0 x1 x2 x3 x4 x5 x6 x7 x8
 * y0 b0 b0 b0 b1 b1 b1 b2 b2 b2
 * y1 b0 b0 b0 b1 b1 b1 b2 b2 b2
 * y2 b0 b0 b0 b1 b1 b1 b2 b2 b2
 * y3 b3 b3 b3 b4 b4 b4 b5 b5 b5
 * y4 b3 b3 b3 b4 b4 b4 b5 b5 b5
 * y5 b3 b3 b3 b4 b4 b4 b5 b5 b5
 * y6 b6 b6 b6 b7 b7 b7 b8 b8 b8
 * y7 b6 b6 b6 b7 b7 b7 b8 b8 b8
 * y8 b6 b6 b6 b7 b7 b7 b8 b8 b8
 */
public class Puzzle {

    private final Cell[][] matrix;
    private final List<Sequence> rows;
    private final List<Sequence> columns;
    private final List<Block> blocks;

    public Puzzle() {

        matrix = new Cell[puzzleSize()][puzzleSize()];

        rows = IntStream.range(0, puzzleSize())
                .mapToObj(i -> new Sequence())
                .collect(Collectors.toList());

        columns = IntStream.range(0, puzzleSize())
                .mapToObj(i -> new Sequence())
                .collect(Collectors.toList());

        blocks = IntStream.range(0, puzzleSize())
                .mapToObj(i -> new Block())
                .collect(Collectors.toList());
    }

    public void setCellAt(int x, int y, Cell cell) {

        matrix[x][y] = cell;

        int rowIndex = xyToRowIndex(x, y);
        int rowCellIndex = xyToRowCellIndex(x, y);
        rows.get(rowIndex).setCellAt(rowCellIndex, cell);

        int columnIndex = xyToColumnIndex(x, y);
        int columnCellIndex = xyToColumnCellIndex(x, y);
        columns.get(columnIndex).setCellAt(columnCellIndex, cell);

        int blockIndex = xyToBlockIndex(x, y);
        int blockCellIndex = xyToBlockCellIndex(x, y);
        blocks.get(blockIndex).setCellAt(blockCellIndex, cell);

    }

    /**
     * Determines whether the puzzle is in a valid state.
     *
     * @return  true, if valid
     *          false, otherwise
     */
    public boolean isValid() {
        /* For the puzzle to be valid, all rows, columns and blocks must be valid */
        return rows.stream().allMatch(Sequence::isValid) && columns.stream().allMatch(Sequence::isValid)
                && blocks.stream().allMatch(Block::isValid);
    }

    @Override
    public String toString() {

        final String blockRowSeparator = "---------------------";
        final String blockColumnSeparator = "|";

        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < puzzleSize(); i++) {

            if (i > 0 && i % puzzleBlockSize() == 0) {
                sb.append(blockRowSeparator).append("\n");
            }

            for (int j = 0; j < puzzleSize(); j++) {

                if (j > 0 && j % puzzleBlockSize() == 0) {
                    sb.append(blockColumnSeparator).append(" ");
                }

                sb.append(matrix[i][j].getValue()).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    private int getRow(int x, int y) {
        return -1;
    }
}
