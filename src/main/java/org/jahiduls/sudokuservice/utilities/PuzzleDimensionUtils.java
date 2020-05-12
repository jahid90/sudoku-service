package org.jahiduls.sudokuservice.utilities;

public final class PuzzleDimensionUtils {

    private static final int BLOCK_SIZE = 3;
    private static final int SIZE = BLOCK_SIZE * BLOCK_SIZE;

    private PuzzleDimensionUtils() {
        // utility class; do not allow instantiation.
    }

    public static int puzzleSize() {
        return SIZE;
    }

    public static int puzzleBlockSize() {
        return BLOCK_SIZE;
    }

    public static int minSequenceIndex() {
        return 0;
    }

    public static int maxSequenceIndex() {
        return SIZE - 1;
    }

    public static int minBlockIndex() {
        return 0;
    }

    public static int maxBlockIndex() {
        return SIZE - 1;
    }
}
