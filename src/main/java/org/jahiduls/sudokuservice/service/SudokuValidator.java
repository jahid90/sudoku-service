package org.jahiduls.sudokuservice.service;

import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.InvalidFormatException;
import org.springframework.stereotype.Component;

@Component
public class SudokuValidator {

    public void validate(Puzzle puzzle) throws InvalidFormatException {
        if (!puzzle.isValid()) {
            throw InvalidFormatException.newException()
                    .withMessage("Not a valid sudoku puzzle")
                    .build();
        }
    }

}
