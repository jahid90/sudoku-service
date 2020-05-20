package org.jahiduls.sudokuservice.service;

import lombok.RequiredArgsConstructor;
import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.UnsolvablePuzzleException;
import org.jahiduls.sudokuservice.service.strategy.PuzzleSolution;
import org.jahiduls.sudokuservice.service.strategy.SolverStrategy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SudokuSolver {

    private final SolverStrategy strategy;

    public Puzzle solve(Puzzle puzzle) throws UnsolvablePuzzleException {
        final PuzzleSolution solution = strategy.solve(puzzle);

        return solution.getPuzzle();
    }

}
