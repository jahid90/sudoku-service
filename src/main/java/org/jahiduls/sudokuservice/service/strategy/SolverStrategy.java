package org.jahiduls.sudokuservice.service.strategy;

import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.InvalidPuzzleException;

public interface SolverStrategy {

    PuzzleSolution solve(Puzzle puzzle) throws InvalidPuzzleException;

}
