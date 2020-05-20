package org.jahiduls.sudokuservice.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;
import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.maxSequenceIndex;
import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.minSequenceIndex;
import static org.jahiduls.sudokuservice.utilities.PuzzleDimensionUtils.puzzleSize;

/**
 * Class representing a sequence of cells. A sequence could be either horizontal or vertical.
 *
 * The positions of the cells are at indices [0-Puzzle.SIZE).
 *
 * A sequence in sudoku generally contains only unique values.
 */
public class Sequence {

    private final List<Cell> cells;

    /*
     * Constructor.
     */
    public Sequence() {
        cells = IntStream.range(0, puzzleSize())
                .mapToObj(i -> Cell.BLANK)
                .collect(Collectors.toList());
    }

    /*
     * Helper copy constructor. Package private.
     */
    Sequence(List<Cell> cells) {

        if (cells.size() != puzzleSize()) {
            throw new RuntimeException("Sequence expects exactly " + puzzleSize() + " cells; got: " + cells.size());
        }

        this.cells = List.copyOf(cells);
    }

    /**
     * Sets a {@link Cell} at the specified index.
     *
     * @param idx The index to be set at.
     * @param cell The {@link Cell} to be set.
     */
    public void setCellAt(int idx, Cell cell) {

        validateIndex(idx);

        cells.set(idx, cell);
    }

    /**
     * Returns the {@link Cell} at the specified index.
     *
     * @param idx The index to fetch the {@link Cell} at.
     *
     * @return The {@link Cell} at the queried index
     */
    public Cell getCellAt(int idx) {

        validateIndex(idx);

        return cells.get(idx);
    }

    /**
     * Checks whether the sequence is a valid one.
     * A valid sequence only has unique cells, except, possibly, for the blank cell.
     *
     * @return  true, if valid
     *          false, otherwise
     */
    public boolean isValid() {

        final Map<Integer, Long> groupings = cells.stream()
                .collect(Collectors.groupingBy(Cell::getValue, Collectors.counting()));

        /*
         * If any value other than 0 occurs more than once, the row is invalid
         */
        return groupings.entrySet().stream()
                .reduce(true, (result, entry) -> result && (entry.getKey() == 0 || entry.getValue() == 1), (a, b) -> a && b);

    }

    /**
     * Returns the set of all unique values in this sequence.
     *
     * @return A set of values.
     */
    public Set<Integer> getAllValues() {
        return cells.stream().map(Cell::getValue).collect(Collectors.toSet());
    }

    /**
     * Returns a set of unique values that can be used as candidates in this sequence.
     *
     * @return A set of values.
     */
    public Set<Integer> getCandidateValues() {

        final Set<Integer> existingValues = getAllValues();

        return IntStream.range(1, 10)
                .boxed()
                .filter(not(existingValues::contains))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return Arrays.toString(cells.toArray());
    }

    private void validateIndex(int idx) {
        if (indexNotInRange(idx)) {
            throw new RuntimeException("Invalid index specified. Expected index to be in ["
                    + minSequenceIndex() + "-" + maxSequenceIndex() + "]; got: " + idx);
        }
    }

    private boolean indexNotInRange(int idx) {
        return idx < minSequenceIndex() || idx > maxSequenceIndex();
    }
}
