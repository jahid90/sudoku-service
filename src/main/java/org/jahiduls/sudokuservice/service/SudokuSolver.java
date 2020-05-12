package org.jahiduls.sudokuservice.service;

import lombok.RequiredArgsConstructor;
import org.jahiduls.sudokuservice.dao.Puzzle;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SudokuSolver {

    private final SolverStrategy strategy;

    public Puzzle solve(Puzzle puzzle) {
        return strategy.solve(puzzle);
    }



}
