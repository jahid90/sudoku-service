package org.jahiduls.sudokuservice.service;

import lombok.extern.log4j.Log4j2;
import org.jahiduls.sudokuservice.dao.Cell;
import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.dao.Row;
import org.jahiduls.sudokuservice.exceptions.InvalidFormatException;
import org.jahiduls.sudokuservice.resource.PuzzleInput;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SudokuParser {

    public Puzzle parse(PuzzleInput puzzle) throws InvalidFormatException {

        final int expectedPuzzleLength = Puzzle.SIZE * Puzzle.SIZE;

        validateLength(puzzle, expectedPuzzleLength);
        validateValues(puzzle);

        int[] values = new int[puzzle.getPuzzle().length()];
        for (int i = 0; i < puzzle.getPuzzle().length(); i++) {
            char currentValue = puzzle.getPuzzle().charAt(i);
            values[i] = Character.getNumericValue(currentValue);
        }

        Puzzle result = new Puzzle();
        for (int i = 0; i < Puzzle.SIZE; i++) {
            Row row = new Row();
            for (int j = i * Puzzle.SIZE; j < (i + 1) * Puzzle.SIZE; j++) {
                row.addCell(
                        Cell.builder().value(values[j]).build()
                );
            }
            result.addRow(row);
        }

        return result;
    }

    private void validateValues(PuzzleInput puzzle) throws InvalidFormatException {
        for (int i = 0; i < puzzle.getPuzzle().length(); i++) {
            char currentValue = puzzle.getPuzzle().charAt(i);

            if (!isValidCellValue(currentValue)) {
                throw InvalidFormatException.newException()
                        .withMessage("Puzzle must contain only numbers [0-9]; got: " + currentValue + " at index: " + i)
                        .build();
            }
        }
    }

    private void validateLength(PuzzleInput puzzle, int expectedPuzzleLength) throws InvalidFormatException {
        if (puzzle.getPuzzle().length() != expectedPuzzleLength) {
            throw InvalidFormatException.newException()
                    .withMessage("Puzzle length must be " + expectedPuzzleLength + "; got: " + puzzle.getPuzzle().length())
                    .build();
        }
    }

    private boolean isValidCellValue(char currentValue) {
        return currentValue >= '0' && currentValue <= '9';
    }

}
