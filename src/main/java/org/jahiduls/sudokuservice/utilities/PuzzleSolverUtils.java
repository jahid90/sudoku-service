package org.jahiduls.sudokuservice.utilities;

import org.jahiduls.sudokuservice.dao.Cell;
import org.jahiduls.sudokuservice.dao.Puzzle;

import java.util.Set;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleSize;

public final class PuzzleSolverUtils {

    private PuzzleSolverUtils() {
        // utility class; prevent initialization
    }

    public static boolean isSolved(Puzzle puzzle) {

        // If any cell is blank, puzzle is not solved
        for (int y = 0; y < puzzleSize(); y++) {
            for (int x = 0; x < puzzleSize(); x++) {
                if (isBlank(puzzle, x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static Set<Integer> candidatesForCell(Puzzle puzzle, int x, int y) {

        if (puzzle.getCellAt(x, y) != Cell.BLANK) {
            throw new RuntimeException("Cell is not blank. Not a valid candidate.");
        }

        final Set<Integer> candidates = puzzle.getCandidateValuesInBlock(x, y);
        candidates.retainAll(puzzle.getCandidateValuesInRow(x, y));
        candidates.retainAll(puzzle.getCandidateValuesInColumn(x, y));

        return candidates;
    }

    public static boolean isBlank(Puzzle puzzle, int x, int y) {
        return Cell.BLANK.equals(puzzle.getCellAt(x, y));
    }
}
