package org.jahiduls.sudokuservice.service.strategy;

import lombok.extern.log4j.Log4j2;
import org.jahiduls.sudokuservice.dao.Cell;
import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.InvalidPuzzleException;
import org.jahiduls.sudokuservice.utilities.PuzzleSolverUtils;

import java.util.Set;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleSize;
import static org.jahiduls.sudokuservice.utilities.PuzzleSolverUtils.isBlank;
import static org.jahiduls.sudokuservice.utilities.PuzzleSolverUtils.isSolved;

@Log4j2
public class BruteForceStrategy implements SolverStrategy {

    @Override
    public PuzzleSolution solve(Puzzle puzzle) throws InvalidPuzzleException {

        log.debug("attempting to solve: \n{}", puzzle);

        if (isSolved(puzzle)) {
            return PuzzleSolution.builder().puzzle(puzzle).solved(true).build();
        }

        for (int y = 0; y < puzzleSize(); y++) {
            for (int x = 0; x < puzzleSize(); x++) {
                if (isBlank(puzzle, x, y)) {

                    log.info("({}, {}) is blank. Attempting brute-force solve with all candidates", x, y);

                    Set<Integer> candidates = PuzzleSolverUtils.candidatesForCell(puzzle, x, y);
                    Cell cell = puzzle.getCellAt(x, y);

                    log.info("Candidates are: {}", candidates);

                    for(Integer candidate : candidates) {

                        // Try to solve with each of the candidates
                        puzzle.setCellAt(x, y, Cell.of(candidate));
                        PuzzleSolution solution = solve(puzzle);
                        if (solution.isSolved()) {
                            return solution;
                        }

                        // If not, revert the cell back to original state and try with the next cell;
                        puzzle.setCellAt(x, y, cell);
                    }
                } else {
                    log.info("({}, {}) is not blank; trying the next slot", x, y);
                }
            }
        }

        throw InvalidPuzzleException.newException().withMessage("Puzzle can not be solved").build();
    }

}
