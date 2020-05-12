package org.jahiduls.sudokuservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jahiduls.sudokuservice.exceptions.InvalidFormatException;
import org.jahiduls.sudokuservice.resource.PuzzleInput;
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
    public String solve(@RequestBody PuzzleInput puzzle) {

        log.info("Received: {}", puzzle);

        try {
            return service.solve(puzzle);
        } catch (InvalidFormatException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

}
