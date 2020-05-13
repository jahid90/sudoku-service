package org.jahiduls.sudokuservice.utilities;

import org.junit.Assert;
import org.junit.Test;

public class PuzzleIteratorUtilsTest {

    /*
     *    c0 c1 c2  c3 c4 c5  c6 c7 c8  (x ->)
     * r0 b0 b0 b0  b1 b1 b1  b2 b2 b2
     * r1 b0 b0 b0  b1 b1 b1  b2 b2 b2
     * r2 b0 b0 b0  b1 b1 b1  b2 b2 b2
     *
     * r3 b3 b3 b3  b4 b4 b4  b5 b5 b5
     * r4 b3 b3 b3  b4 b4 b4  b5 b5 b5
     * r5 b3 b3 b3  b4 b4 b4  b5 b5 b5
     *
     * r6 b6 b6 b6  b7 b7 b7  b8 b8 b8
     * r7 b6 b6 b6  b7 b7 b7  b8 b8 b8
     * r8 b6 b6 b6  b7 b7 b7  b8 b8 b8
     *
     * (y)
     * ( )
     * (|)
     * (v)
     */

    @Test
    public void generatesRowNumberCorrectly() {
        Assert.assertEquals(0, PuzzleIteratorUtils.xyToRowIndex(6, 0));
    }

    @Test
    public void generatesRowCellIndexCorrectly() {
        Assert.assertEquals(6, PuzzleIteratorUtils.xyToRowCellIndex(6, 0));
    }

    @Test
    public void generatesColumnNumberCorrectly() {
        Assert.assertEquals(7, PuzzleIteratorUtils.xyToColumnIndex(7, 5));
    }

    @Test
    public void generatesColumnCellIndexCorrectly() {
        Assert.assertEquals(5, PuzzleIteratorUtils.xyToColumnCellIndex(7, 5));
    }

    @Test
    public void generatesBlockNumberCorrectly() {
        Assert.assertEquals(7, PuzzleIteratorUtils.xyToBlockIndex(4, 6));
    }

    @Test
    public void generatesBlockCellIndexCorrectly() {
        Assert.assertEquals(1, PuzzleIteratorUtils.xyToBlockCellIndex(4, 6));
    }
}
