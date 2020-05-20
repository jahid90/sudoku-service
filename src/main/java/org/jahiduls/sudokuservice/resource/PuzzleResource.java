package org.jahiduls.sudokuservice.resource;

import lombok.Getter;
import lombok.ToString;
import org.jahiduls.sudokuservice.dao.Puzzle;

import java.util.stream.IntStream;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleSize;

@ToString
public class PuzzleResource {

    @Getter
    private String puzzle;

    public static PuzzleResource fromPuzzle(Puzzle puzzle) {

        StringBuilder sb = new StringBuilder();

        IntStream.range(0, puzzleSize())
                .forEach(y -> IntStream.range(0, puzzleSize())
                .forEach(x -> sb.append(puzzle.getCellAt(x, y).getValue())));

        final PuzzleResource result = new PuzzleResource();
        result.puzzle = sb.toString();

        return result;
    }

    public static PuzzleResource fromString(String puzzle) {
        final PuzzleResource result = new PuzzleResource();
        result.puzzle = puzzle;

        return result;
    }
}
