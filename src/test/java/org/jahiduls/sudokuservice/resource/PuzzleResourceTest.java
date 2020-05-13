package org.jahiduls.sudokuservice.resource;

import org.jahiduls.sudokuservice.dao.Cell;
import org.jahiduls.sudokuservice.dao.Puzzle;
import org.junit.Assert;
import org.junit.Test;

public class PuzzleResourceTest {

    @Test
    public void canCreatePuzzleResourceFromPuzzle() {
        final Puzzle puzzle = new Puzzle();
        puzzle.setCellAt(0, 5, Cell.FIVE);
        puzzle.setCellAt(4, 2, Cell.THREE);

        final String expected = "000000000000000000000030000000000000000000000500000000000000000000000000000000000";

        PuzzleResource resource = PuzzleResource.fromPuzzle(puzzle);

        Assert.assertEquals(expected, resource.getPuzzle());
    }

    @Test
    public void canCreatePuzzleFromString() {
        final String puzzle = "000000000000000000000030000000000000000000000500000000000000000000000000000000000";

        final PuzzleResource resource = PuzzleResource.fromString(puzzle);

        Assert.assertNotNull(resource);
    }
}
