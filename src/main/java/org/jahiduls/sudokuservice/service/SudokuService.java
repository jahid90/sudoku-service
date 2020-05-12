package org.jahiduls.sudokuservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.InvalidFormatException;
import org.jahiduls.sudokuservice.resource.PuzzleInput;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SudokuService {

    private final SudokuParser parser;
    private final SudokuValidator validator;
    private final SudokuSolver solver;

    /**
     * Solves a sudoku puzzle
     * @param puzzle The puzzle to solve
     * @return The solved puzzle
     */
    public String solve(PuzzleInput puzzle) throws InvalidFormatException {

        Puzzle parsedPuzzle = parser.parse(puzzle);

        log.info("Puzzle parsed");
        log.debug("\n{}", parsedPuzzle);

        validator.validate(parsedPuzzle);

        log.info("Puzzle validated.");

        return "solved!";
    }

}
