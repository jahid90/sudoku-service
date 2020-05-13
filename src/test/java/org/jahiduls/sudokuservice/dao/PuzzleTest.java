package org.jahiduls.sudokuservice.dao;

import org.jahiduls.sudokuservice.resource.PuzzleResource;
import org.jahiduls.sudokuservice.service.SudokuParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleSize;

public class PuzzleTest {

    private Puzzle puzzle;

    @Before
    public void setUp() throws Exception {
        puzzle = new Puzzle();
    }

    @Test
    public void copyConstructorCreatesACopy() {
        puzzle.setCellAt(0, 0, Cell.NINE);

        final Puzzle copy = Puzzle.from(puzzle);

        assertPuzzleEquals(puzzle, copy);
        Assert.assertNotSame(puzzle, copy);
    }

    @Test
    public void canSetAndGetCellsCorrectly() {
        Assert.assertEquals(Cell.BLANK, puzzle.getCellAt(5, 6));
        puzzle.setCellAt(5, 6, Cell.SEVEN);
        Assert.assertEquals(Cell.SEVEN, puzzle.getCellAt(5, 6));
    }

    @Test
    public void checksValidityCorrectly() {

        puzzle.setCellAt(0, 0, Cell.ONE);
        puzzle.setCellAt(1, 1, Cell.TWO);
        puzzle.setCellAt(2, 2, Cell.THREE);
        puzzle.setCellAt(3, 3, Cell.ONE);
        puzzle.setCellAt(4, 4, Cell.TWO);
        puzzle.setCellAt(5, 5, Cell.THREE);
        puzzle.setCellAt(6, 6, Cell.ONE);
        puzzle.setCellAt(7, 7, Cell.TWO);
        puzzle.setCellAt(8, 8, Cell.THREE);


        Assert.assertTrue(puzzle.isValid());
    }

    @Test
    public void checksRowInvalidityCorrectly() {
        puzzle.setCellAt(3, 7, Cell.NINE);
        puzzle.setCellAt(6, 7, Cell.NINE);

        Assert.assertFalse(puzzle.isValid());
    }

    @Test
    public void checksColumnInvalidityCorrectly() {
        puzzle.setCellAt(3, 0, Cell.NINE);
        puzzle.setCellAt(3, 7, Cell.NINE);

        Assert.assertFalse(puzzle.isValid());
    }

    @Test
    public void checksBlockInvalidityCorrectly() {
        puzzle.setCellAt(0, 1, Cell.NINE);
        puzzle.setCellAt(2, 2, Cell.NINE);

        Assert.assertFalse(puzzle.isValid());
    }

    @Test
    public void getsAllValuesInRowCorrectly() {
        int row = 4;
        puzzle.setCellAt(0, row, Cell.FIVE);
        puzzle.setCellAt(8, row, Cell.FOUR);
        puzzle.setCellAt(6, row, Cell.SEVEN);

        final Set<Integer> expected = Set.of(0, 4, 5, 7);

        Assert.assertEquals(expected, puzzle.getAllValuesInRow(0, row));
    }

    @Test
    public void getsAllCandidatesInRowCorrectly() {
        int row = 4;
        puzzle.setCellAt(0, row, Cell.FIVE);
        puzzle.setCellAt(8, row, Cell.FOUR);
        puzzle.setCellAt(6, row, Cell.SEVEN);

        final Set<Integer> expected = Set.of(1, 2, 3, 6, 8, 9);

        Assert.assertEquals(expected, puzzle.getCandidateValuesInRow(0, row));
    }

    @Test
    public void getsAllValuesInColumnCorrectly() {
        int column = 8;
        puzzle.setCellAt(column, 0, Cell.FIVE);
        puzzle.setCellAt(column, 8, Cell.FOUR);
        puzzle.setCellAt(column, 6, Cell.SEVEN);

        final Set<Integer> expected = Set.of(0, 4, 5, 7);

        Assert.assertEquals(expected, puzzle.getAllValuesInColumn(column, 5));
    }

    @Test
    public void getsAllCandidatesInColumnCorrectly() {
        int column = 8;
        puzzle.setCellAt(column, 0, Cell.FIVE);
        puzzle.setCellAt(column, 8, Cell.FOUR);
        puzzle.setCellAt(column, 6, Cell.SEVEN);

        final Set<Integer> expected = Set.of(1, 2, 3, 6, 8, 9);

        Assert.assertEquals(expected, puzzle.getCandidateValuesInColumn(column, 2));
    }

    @Test
    public void getsAllValuesInBlockCorrectly() {
        puzzle.setCellAt(0, 0, Cell.FIVE);
        puzzle.setCellAt(1, 2, Cell.FOUR);
        puzzle.setCellAt(2, 1, Cell.SEVEN);

        final Set<Integer> expected = Set.of(0, 4, 5, 7);

        Assert.assertEquals(expected, puzzle.getAllValuesInBlock(1, 1));
    }

    @Test
    public void getsAllCandidatesInBlockCorrectly() {
        puzzle.setCellAt(0, 0, Cell.FIVE);
        puzzle.setCellAt(1, 2, Cell.FOUR);
        puzzle.setCellAt(2, 1, Cell.SEVEN);

        final Set<Integer> expected = Set.of(1, 2, 3, 6, 8, 9);

        Assert.assertEquals(expected, puzzle.getCandidateValuesInBlock(1, 1));
    }

    @Test
    public void prettyPrintsItself() {
        puzzle.setCellAt(0, 5, Cell.FIVE);
        puzzle.setCellAt(8, 6, Cell.FOUR);
        puzzle.setCellAt(6, 7, Cell.SEVEN);
        puzzle.setCellAt(7, 0, Cell.FIVE);
        puzzle.setCellAt(8, 8, Cell.FOUR);
        puzzle.setCellAt(6, 6, Cell.SEVEN);
        puzzle.setCellAt(0, 0, Cell.FIVE);
        puzzle.setCellAt(1, 2, Cell.FOUR);
        puzzle.setCellAt(2, 1, Cell.SEVEN);

        final String expected =
                "-------------------------\n" +
                "| 5 0 0 | 0 0 0 | 0 5 0 |\n" +
                "| 0 0 7 | 0 0 0 | 0 0 0 |\n" +
                "| 0 4 0 | 0 0 0 | 0 0 0 |\n" +
                "-------------------------\n" +
                "| 0 0 0 | 0 0 0 | 0 0 0 |\n" +
                "| 0 0 0 | 0 0 0 | 0 0 0 |\n" +
                "| 5 0 0 | 0 0 0 | 0 0 0 |\n" +
                "-------------------------\n" +
                "| 0 0 0 | 0 0 0 | 7 0 4 |\n" +
                "| 0 0 0 | 0 0 0 | 7 0 0 |\n" +
                "| 0 0 0 | 0 0 0 | 0 0 4 |\n" +
                "-------------------------\n";

        Assert.assertEquals(expected, puzzle.toString());

    }

    private void assertPuzzleEquals(Puzzle first, Puzzle second) {
        for (int i = 0; i < puzzleSize(); i++) {
            for (int j = 0; j < puzzleSize(); j++) {
                Assert.assertEquals(first.getCellAt(i, j), second.getCellAt(i, j));
            }
        }
    }
}

