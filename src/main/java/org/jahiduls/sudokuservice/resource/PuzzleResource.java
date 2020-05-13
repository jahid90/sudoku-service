package org.jahiduls.sudokuservice.resource;

import lombok.Getter;
import org.jahiduls.sudokuservice.dao.Puzzle;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleSize;

public class PuzzleResource {

    @Getter
    private String puzzle;

    public static PuzzleResource fromPuzzle(Puzzle puzzle) {

        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < puzzleSize(); y++) {
            for (int x = 0; x < puzzleSize(); x++) {
                sb.append(puzzle.getCellAt(x, y).getValue());
            }
        }

        PuzzleResource result = new PuzzleResource();
        result.puzzle = sb.toString();

        return result;
    }

    public static PuzzleResource fromString(String puzzle) {
        final PuzzleResource result = new PuzzleResource();
        result.puzzle = puzzle;

        return result;
    }

}
