package org.jahiduls.sudokuservice.service;

import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.InvalidPuzzleException;
import org.springframework.stereotype.Component;

@Component
public class SudokuValidator {

    public void validate(Puzzle puzzle) throws InvalidPuzzleException {
        if (!puzzle.isValid()) {
            throw InvalidPuzzleException.newException()
                    .withMessage("Not a valid sudoku puzzle")
                    .build();
        }
    }

}
