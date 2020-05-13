package org.jahiduls.sudokuservice.utilities;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleBlockSize;

/**
 * Utilities related to iterating over the puzzle data structures.
 *
 * Provides easy transformation between x-y axes bases co-ordinates to row, column and block based indices.
 */
public final class PuzzleIteratorUtils {

    private PuzzleIteratorUtils() {
        // utility class; do not allow instantiation.
    }

    public static int xyToRowIndex(int x, int y) {
        return y;
    }

    public static int xyToRowCellIndex(int x, int y) {
        return x;
    }

    public static int xyToColumnIndex(int x, int y) {
        return x;
    }

    public static int xyToColumnCellIndex(int x, int y) {
        return y;
    }

    public static int xyToBlockIndex(int x, int y) {
        int blockX = x / 3;
        int blockY = y / 3;

        return puzzleBlockSize() * blockY + blockX;
    }

    public static int xyToBlockCellIndex(int x, int y) {
        int blockX = x % 3;
        int blockY = y % 3;

        return puzzleBlockSize() * blockY + blockX;
    }

}
