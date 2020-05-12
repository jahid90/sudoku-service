package org.jahiduls.sudokuservice.dao;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;
import java.util.stream.Collectors;

public class SequenceTest {

    private Sequence sequence;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        sequence = new Sequence();
    }

    @Test
    public void throwsExceptionOnAttemptToSetCellBelowLimit() {

        expected.expect(RuntimeException.class);

        sequence.setCellAt(-1, Cell.TWO);
    }

    @Test
    public void throwsExceptionOnAttemptToSetCellAboveLimit() {

        expected.expect(RuntimeException.class);

        sequence.setCellAt(9, Cell.ONE);
    }

    @Test
    public void throwsExceptionOnAttemptToGetCellBelowLimit() {

        expected.expect(RuntimeException.class);

        sequence.getCellAt(-1);
    }

    @Test
    public void throwsExceptionOnAttemptToGetCellAboveLimit() {

        expected.expect(RuntimeException.class);

        sequence.getCellAt(9);
    }

    @Test
    public void canSetAndGetCellsCorrectly() {
        sequence.setCellAt(2, Cell.ONE);
        sequence.setCellAt(5, Cell.EIGHT);

        Assert.assertEquals(Cell.BLANK, sequence.getCellAt(0));
        Assert.assertEquals(Cell.BLANK, sequence.getCellAt(1));
        Assert.assertEquals(Cell.ONE, sequence.getCellAt(2));
        Assert.assertEquals(Cell.BLANK, sequence.getCellAt(3));
        Assert.assertEquals(Cell.BLANK, sequence.getCellAt(4));
        Assert.assertEquals(Cell.EIGHT, sequence.getCellAt(5));
        Assert.assertEquals(Cell.BLANK, sequence.getCellAt(6));
        Assert.assertEquals(Cell.BLANK, sequence.getCellAt(7));
        Assert.assertEquals(Cell.BLANK, sequence.getCellAt(8));
    }

    @Test
    public void copyConstructorThrowsExceptionIfInputSizeIsUnexpected() {

        expected.expect(RuntimeException.class);

        sequence = new Sequence(Lists.emptyList());
    }

    @Test
    public void checksValidityCorrectly() {
        sequence.setCellAt(3, Cell.THREE);
        sequence.setCellAt(4, Cell.FIVE);

        Assert.assertTrue(sequence.isValid());
    }

    @Test
    public void checksInvalidityCorrectly() {
        sequence.setCellAt(4, Cell.FIVE);
        sequence.setCellAt(7, Cell.FIVE);
        sequence.setCellAt(8, Cell.TWO);

        Assert.assertFalse(sequence.isValid());
    }

    @Test
    public void canGetAllValuesCorrectly() {
        sequence.setCellAt(0, Cell.FOUR);
        sequence.setCellAt(1, Cell.TWO);
        sequence.setCellAt(5, Cell.SIX);

        final Set<Integer> expected = Set.of(Cell.BLANK, Cell.FOUR, Cell.TWO, Cell.SIX)
                .stream()
                .map(Cell::getValue)
                .collect(Collectors.toSet());

        Assert.assertEquals(expected, sequence.getAllValues());
    }

    @Test
    public void canGetCandidatesCorrectly() {
        sequence.setCellAt(0, Cell.FOUR);
        sequence.setCellAt(1, Cell.TWO);
        sequence.setCellAt(5, Cell.SIX);
        sequence.setCellAt(7, Cell.SEVEN);

        final Set<Integer> expected = Set.of(Cell.ONE, Cell.THREE, Cell.FIVE, Cell.EIGHT, Cell.NINE)
                .stream()
                .map(Cell::getValue)
                .collect(Collectors.toSet());

        Assert.assertEquals(expected, sequence.getCandidateValues());
    }

    @Test
    public void prettyPrintsItself() {
        sequence.setCellAt(0, Cell.FIVE);
        sequence.setCellAt(8, Cell.FOUR);

        final String expected = "[5, 0, 0, 0, 0, 0, 0, 0, 4]";

        Assert.assertEquals(expected, sequence.toString());
    }
}
