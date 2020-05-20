package org.jahiduls.sudokuservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.InvalidFormatException;
import org.jahiduls.sudokuservice.exceptions.InvalidPuzzleException;
import org.jahiduls.sudokuservice.exceptions.UnsolvablePuzzleException;
import org.jahiduls.sudokuservice.resource.PuzzleResource;
import org.jahiduls.sudokuservice.service.SudokuService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@RestController
@RequiredArgsConstructor
public class SudokuController {

    private final SudokuService service;

    @PostMapping(path = "/solve")
    public PuzzleResource solve(@RequestBody PuzzleResource puzzle) {

        log.info("Received: {}", puzzle);

        try {
            final Puzzle solution = service.solve(puzzle);
            return PuzzleResource.fromPuzzle(solution);
        } catch (InvalidFormatException | InvalidPuzzleException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (UnsolvablePuzzleException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

}
