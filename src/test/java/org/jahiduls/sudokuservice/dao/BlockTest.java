package org.jahiduls.sudokuservice.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;
import java.util.stream.Collectors;

public class BlockTest {

    private Block block;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        block = new Block();
    }

    @Test
    public void throwsExceptionOnAttemptToSetCellBelowLimit() {

        expectedException.expect(RuntimeException.class);

        block.setCellAt(-1, Cell.TWO);
    }

    @Test
    public void throwsExceptionOnAttemptToSetCellAboveLimit() {

        expectedException.expect(RuntimeException.class);

        block.setCellAt(9, Cell.ONE);
    }

    @Test
    public void throwsExceptionOnAttemptToGetCellBelowLimit() {

        expectedException.expect(RuntimeException.class);

        block.getCellAt(-1);
    }

    @Test
    public void throwsExceptionOnAttemptToGetCellAboveLimit() {

        expectedException.expect(RuntimeException.class);

        block.getCellAt(9);
    }

    @Test
    public void canSetAndGetCellsCorrectly() {

        block.setCellAt(1, Cell.THREE);
        block.setCellAt(6, Cell.NINE);

        Assert.assertEquals(Cell.BLANK, block.getCellAt(0));
        Assert.assertEquals(Cell.THREE, block.getCellAt(1));
        Assert.assertEquals(Cell.BLANK, block.getCellAt(2));
        Assert.assertEquals(Cell.BLANK, block.getCellAt(3));
        Assert.assertEquals(Cell.BLANK, block.getCellAt(4));
        Assert.assertEquals(Cell.BLANK, block.getCellAt(5));
        Assert.assertEquals(Cell.NINE, block.getCellAt(6));
        Assert.assertEquals(Cell.BLANK, block.getCellAt(7));
        Assert.assertEquals(Cell.BLANK, block.getCellAt(8));
    }

    @Test
    public void checksValidityCorrectly() {
        block.setCellAt(1, Cell.FOUR);
        block.setCellAt(8, Cell.SIX);

        Assert.assertTrue(block.isValid());
    }

    @Test
    public void checksInvalidityCorrectly() {
        block.setCellAt(3, Cell.NINE);
        block.setCellAt(6, Cell.NINE);

        Assert.assertFalse(block.isValid());
    }

    @Test
    public void getsValuesCorrectly() {
        block.setCellAt(1, Cell.TWO);
        block.setCellAt(5, Cell.FOUR);
        block.setCellAt(6, Cell.NINE);

        final Set<Integer> expected = Set.of(Cell.BLANK, Cell.TWO, Cell.FOUR, Cell.NINE)
                .stream()
                .map(Cell::getValue)
                .collect(Collectors.toSet());

        Assert.assertEquals(expected, block.getAllValues());
    }

    @Test
    public void getsCandidatesCorrectly() {
        block.setCellAt(1, Cell.TWO);
        block.setCellAt(5, Cell.FOUR);
        block.setCellAt(6, Cell.NINE);
        block.setCellAt(7, Cell.THREE);
        block.setCellAt(8, Cell.SEVEN);

        final Set<Integer> expected = Set.of(Cell.ONE, Cell.FIVE, Cell.SIX, Cell.EIGHT)
                .stream()
                .map(Cell::getValue)
                .collect(Collectors.toSet());

        Assert.assertEquals(expected, block.getCandidateValues());
    }

    @Test
    public void prettyPrintsItself() {
        block.setCellAt(1, Cell.TWO);
        block.setCellAt(5, Cell.FOUR);
        block.setCellAt(6, Cell.NINE);
        block.setCellAt(7, Cell.THREE);
        block.setCellAt(8, Cell.SEVEN);

        final String expected =
                "0 2 0 \n" +
                "0 0 4 \n" +
                "9 3 7 \n";

        Assert.assertEquals(expected, block.toString());
    }
}
