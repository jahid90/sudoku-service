package org.jahiduls.sudokuservice.service.strategy;

import lombok.Builder;
import lombok.Getter;
import org.jahiduls.sudokuservice.dao.Puzzle;

@Builder
public class PuzzleSolution {

    @Getter
    private final Puzzle puzzle;

    @Getter
    private final boolean solved;

}
