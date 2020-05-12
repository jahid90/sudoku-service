package org.jahiduls.sudokuservice.dao;

import org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.maxBlockIndex;
import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.minBlockIndex;
import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleBlockSize;

/**
 * A block is `BLOCK_SIZE` x `BLOCK_SIZE` in dimension.
 *
 * Block cell positions are defined as below:
 *
 * 0  1  2
 * 3  4  5
 * 6  7  8
 */
public class Block {

    private final List<Cell> cells;

    public Block() {
        cells = IntStream.range(0, PuzzleDimensionUtils.puzzleSize())
                .mapToObj(i -> Cell.BLANK)
                .collect(Collectors.toList());
    }

    /**
     * Sets the {@link Cell} at the specified index.
     *
     * @param idx The index to set at.
     * @param cell The {@link Cell} to set.
     */
    public void setCellAt(int idx, Cell cell) {

        validateIndex(idx);

        cells.set(idx, cell);
    }

    /**
     * Retrieves the {@link Cell} at the specified index.
     *
     * @param idx The index to retrieve the {@link Cell} from.
     *
     * @return The {@link Cell} at the specified index.
     */
    public Cell getCellAt(int idx) {

        validateIndex(idx);

        return cells.get(idx);
    }

    /**
     * Checks whether the block is a valid one.
     * A valid block pny has unique cells, except, possibly, for the blank cell.
     *
     * @return  true, if valid
     *          false, otherwise
     */
    public boolean isValid() {
        Sequence row = new Sequence(this.cells);

        return row.isValid();
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < puzzleBlockSize(); i++) {
            for (int j = 0; j < puzzleBlockSize(); j++) {
                int linearIdx = puzzleBlockSize() * i + j;
                sb.append(getCellAt(linearIdx)).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private void validateIndex(int idx) {
        if (indexNotInRange(idx)) {
            throw new RuntimeException("Invalid index specified. Expected index to be in ["
                    + minBlockIndex() + "-" + maxBlockIndex() + "]; got: " + idx);
        }
    }

    private boolean indexNotInRange(int idx) {
        return idx < minBlockIndex() || idx > maxBlockIndex();
    }

}
