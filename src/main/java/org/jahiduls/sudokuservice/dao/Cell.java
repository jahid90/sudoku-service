package org.jahiduls.sudokuservice.dao;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
public class Cell {

    public static final Cell BLANK = Cell.builder().value(0).build();
    public static final Cell ONE = Cell.builder().value(1).build();
    public static final Cell TWO = Cell.builder().value(2).build();
    public static final Cell THREE = Cell.builder().value(3).build();
    public static final Cell FOUR = Cell.builder().value(4).build();
    public static final Cell FIVE = Cell.builder().value(5).build();
    public static final Cell SIX = Cell.builder().value(6).build();
    public static final Cell SEVEN = Cell.builder().value(7).build();
    public static final Cell EIGHT = Cell.builder().value(8).build();
    public static final Cell NINE = Cell.builder().value(9).build();

    @Getter
    private final Integer value;

    public static Cell of(int value) {
        switch (value) {
            case 0: return BLANK;
            case 1: return ONE;
            case 2: return TWO;
            case 3: return THREE;
            case 4: return FOUR;
            case 5: return FIVE;
            case 6: return SIX;
            case 7: return SEVEN;
            case 8: return EIGHT;
            case 9: return NINE;
            default: throw new RuntimeException("Invalid cell value. Should be in [0-9]");
        }
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
