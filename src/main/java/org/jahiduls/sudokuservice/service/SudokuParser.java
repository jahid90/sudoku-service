package org.jahiduls.sudokuservice.service;

import lombok.extern.log4j.Log4j2;
import org.jahiduls.sudokuservice.dao.Cell;
import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.InvalidFormatException;
import org.jahiduls.sudokuservice.resource.PuzzleResource;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleSize;

@Log4j2
@Component
public class SudokuParser {

    public Puzzle parse(PuzzleResource puzzle) throws InvalidFormatException {

        validateLength(puzzle);
        validateValues(puzzle);

        final int[] values = parseValuesAsInt(puzzle);

        return generatePuzzle(values);
    }

    private void validateLength(PuzzleResource puzzle) throws InvalidFormatException {

        final int expectedPuzzleLength = puzzleSize() * puzzleSize();

        if (puzzle.getPuzzle().length() != expectedPuzzleLength) {
            throw InvalidFormatException.newException()
                    .withMessage("Puzzle length must be " + expectedPuzzleLength + "; got: " + puzzle.getPuzzle().length())
                    .build();
        }
    }

    private void validateValues(PuzzleResource puzzle) throws InvalidFormatException {

        for (int i = 0; i < puzzle.getPuzzle().length(); i++) {
            char currentValue = puzzle.getPuzzle().charAt(i);

            if (!isValidCellValue(currentValue)) {
                throw InvalidFormatException.newException()
                        .withMessage("Puzzle must contain only numbers [0-9]; got: " + currentValue + " at index: " + i)
                        .build();
            }
        }
    }

    private boolean isValidCellValue(char currentValue) {
        return currentValue >= '0' && currentValue <= '9';
    }

    private int[] parseValuesAsInt(PuzzleResource puzzle) {

        final int[] values = new int[puzzle.getPuzzle().length()];

        IntStream.range(0, puzzle.getPuzzle().length())
                .forEach(i -> {
                    final char currentValue = puzzle.getPuzzle().charAt(i);
                    values[i] = Character.getNumericValue(currentValue);
        });

        return values;
    }

    private Puzzle generatePuzzle(int[] values) {

        final Puzzle result = new Puzzle();

        IntStream.range(0, values.length)
                .forEach(i -> {
                    int x = i % puzzleSize();
                    int y = i / puzzleSize();

                    result.setCellAt(x, y, Cell.of(values[i]));
        });

        return result;
    }

}
