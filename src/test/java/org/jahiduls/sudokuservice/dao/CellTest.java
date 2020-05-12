package org.jahiduls.sudokuservice.dao;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.stream.IntStream;

public class CellTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void cellsCreatedFromFactoryMethodWithSameValueAreSame() {

        Integer value = 1;

        Cell first = Cell.of(value);
        Cell second = Cell.of(value);

        Assert.assertEquals("Objects should be equal", first, second);
        Assert.assertSame("References should be equal", first, second);
    }

    @Test
    public void cellsCreatedWithBuilderWithSameValueAreDifferent() {

        Integer value = 1;

        Cell first = Cell.builder().value(value).build();
        Cell second = Cell.builder().value(value).build();

        Assert.assertEquals("Objects should be equal", first, second);
        Assert.assertNotSame("References should not be equal", first, second);
    }

    @Test
    public void canCreateCellsViaFactoryMethod() {
        IntStream.range(0, 10)
                .forEach(Cell::of);
    }

    @Test
    public void factoryMethodThrowsExceptionForInvalidLowerValue() {
        expected.expect(RuntimeException.class);

        Cell.of(-1);
    }

    @Test
    public void factoryMethodThrowsExceptionForInvalidHigherValue() {
        expected.expect(RuntimeException.class);

        Cell.of(10);
    }
}
