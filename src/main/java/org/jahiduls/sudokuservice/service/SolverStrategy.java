package org.jahiduls.sudokuservice.service;

import org.jahiduls.sudokuservice.dao.Puzzle;

public interface SolverStrategy {

    Puzzle solve(Puzzle puzzle);

}
