package org.jahiduls.sudokuservice.utilities;

import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.resource.PuzzleResource;
import org.jahiduls.sudokuservice.service.SudokuParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

public class PuzzleSolverUtilsTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    private SudokuParser parser;

    /*
     *   012 345 678
     * 0 827 154 396
     * 1 965 327 148
     * 2 341 689 752
     *
     * 3 593 468 271
     * 4 472 513 689
     * 5 618 972 435
     *
     * 6 786 235 914
     * 7 154 796 923
     * 8 239 841 567
     */
    private String solved = "827154396965327148341689752593468271472513689618972435786235914154796923239841567";

    /*
     *   012 345 678
     * 0 020 004 300
     * 1 900 020 008
     * 2 000 609 050
     *
     * 3 000 000 001
     * 4 072 503 680
     * 5 600 000 000
     *
     * 6 080 205 000
     * 7 100 090 003
     * 8 009 800 060
     */
    private String unsolved = "020004300900020008000609050000000001072503680600000000080205000100090003009800060";

    @Before
    public void setUp() throws Exception {
        parser = new SudokuParser();
    }

    @Test
    public void checksUnsolvedPuzzleCorrectly() throws Exception {
        final Puzzle puzzle = parser.parse(PuzzleResource.fromString(unsolved));

        Assert.assertFalse(PuzzleSolverUtils.isSolved(puzzle));
    }

    @Test
    public void checksSolvedPuzzleCorrectly() throws Exception {
        final Puzzle puzzle = parser.parse(PuzzleResource.fromString(solved));

        Assert.assertTrue(PuzzleSolverUtils.isSolved(puzzle));
    }

    @Test
    public void attemptingToGetCandidatesForAFilledInCellRaisesAnException() throws Exception {

        expected.expect(RuntimeException.class);

        final Puzzle puzzle = parser.parse(PuzzleResource.fromString(solved));

        PuzzleSolverUtils.candidatesForCell(puzzle, 0, 0);
    }

    @Test
    public void fetchesCandidatesCorrectlyForARequestedCell() throws Exception {

        final Puzzle puzzle = parser.parse(PuzzleResource.fromString(unsolved));

        final Set<Integer> expected = Set.of(1, 7);

        final Set<Integer> candidates = PuzzleSolverUtils.candidatesForCell(puzzle, 5, 1);

        Assert.assertEquals(expected, candidates);
    }
}
