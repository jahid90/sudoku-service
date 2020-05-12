package org.jahiduls.sudokuservice.dao;

import org.junit.Assert;
import org.junit.Test;

public class CellTest {

    @Test
    public void cellsCreatedFromFactoryMethodWithSameValueAreSame() {

        Integer value = 1;

        Cell first = Cell.of(value);
        Cell second = Cell.of(value);

        Assert.assertEquals("Objects should be equal", first, second);
        Assert.assertTrue("References should be equal", first == second);
    }

    @Test
    public void cellsCreatedWithBuilderWithSameValueAreDifferent() {

        Integer value = 1;

        Cell first = Cell.builder().value(value).build();
        Cell second = Cell.builder().value(value).build();

        Assert.assertEquals("Objects should be equal", first, second);
        Assert.assertFalse("References should not be equal", first == second);
    }
}
