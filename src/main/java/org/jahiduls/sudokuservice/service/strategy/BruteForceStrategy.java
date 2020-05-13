package org.jahiduls.sudokuservice.service.strategy;

import lombok.extern.log4j.Log4j2;
import org.jahiduls.sudokuservice.dao.Cell;
import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.InvalidPuzzleException;

import java.util.Set;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleSize;
import static org.jahiduls.sudokuservice.utilities.PuzzleSolverUtils.candidatesForCell;
import static org.jahiduls.sudokuservice.utilities.PuzzleSolverUtils.isBlank;
import static org.jahiduls.sudokuservice.utilities.PuzzleSolverUtils.isSolved;

@Log4j2
public class BruteForceStrategy implements SolverStrategy {

    @Override
    public PuzzleSolution solve(Puzzle puzzle) throws InvalidPuzzleException {

        final PuzzleSolution solution = solveFromPosition(puzzle, 0, 0);

        if (solution.isSolved()) {
            log.info("Puzzle is solved! \n{}", solution.getPuzzle());

            return solution;
        }

        log.info("Puzzle could not be solved!");

        throw InvalidPuzzleException.newException().withMessage("Puzzle can not be solved").build();
    }

    private PuzzleSolution solveFromPosition(Puzzle puzzle, int x, int y) {

        log.debug("({}, {}) -> \n{}", x, y, puzzle);

        // If puzzle is solved, return it
        if (isSolved(puzzle)) {
            return PuzzleSolution.builder().puzzle(puzzle).solved(true).build();
        }

        if (isBlank(puzzle, x, y)) {
            // Try with candidates in the current position
            log.debug("Cell is blank at ({}, {}). Trying with candidates.", x, y);

            return solveWithCandidatesAt(x, y, puzzle);
        } else {
            // Else, try the next position
            int nextX = (x + 1) % puzzleSize();
            int nextY = y + ((x + 1) / puzzleSize());
            log.debug("Cell is not blank at ({}, {}). Trying candidates at the next position at ({}, {})", x, y, nextX, nextY);

            return solveFromPosition(puzzle, nextX, nextY);
        }
    }

    private PuzzleSolution solveWithCandidatesAt(int x, int y, Puzzle puzzle) {

        final Set<Integer> candidates = candidatesForCell(puzzle, x, y);
        final Cell cell = puzzle.getCellAt(x, y); // Should be blank?

        log.debug("Candidates at ({}, {}) are: {}", x, y, candidates);

        for (Integer candidate : candidates) {

            // Update current position with a candidate
            puzzle.setCellAt(x, y, Cell.of(candidate));

            log.debug("Placed candidate: {} at ({}, {})", candidate, x, y);

            // Attempt solution from the next position
            int nextX = (x + 1) % puzzleSize();
            int nextY = y + ((x + 1) / puzzleSize());

            final PuzzleSolution solution = solveFromPosition(puzzle, nextX, nextY);
            if (solution.isSolved()) {
                return solution;
            }

            // If puzzle has not yet been solved, revert the cell back to original state and try with the next cell;
            puzzle.setCellAt(x, y, cell);

            log.debug("Restored candidate: {} at ({}, {})", candidate, x, y);
        }

        return PuzzleSolution.builder().puzzle(puzzle).solved(false).build();
    }

}
