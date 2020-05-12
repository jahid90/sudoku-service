package org.jahiduls.sudokuservice.controller;

import org.jahiduls.sudokuservice.dao.Puzzle;
import org.jahiduls.sudokuservice.exceptions.InvalidFormatException;
import org.jahiduls.sudokuservice.exceptions.InvalidPuzzleException;
import org.jahiduls.sudokuservice.resource.PuzzleResource;
import org.jahiduls.sudokuservice.service.SudokuService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class SudokuControllerTest {

    private SudokuController controller;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    private SudokuService service;


    private PuzzleResource puzzleResource;

    @Before
    public void setUp() throws Exception {
        controller = new SudokuController(service);

        puzzleResource = new PuzzleResource();
    }

    @Test
    public void callToSolveDelegatesToService() throws Exception {
        Mockito.when(service.solve(puzzleResource)).thenReturn(new Puzzle());

        controller.solve(puzzleResource);

        Mockito.verify(service, times(1)).solve(puzzleResource);
    }

    @Test
    public void callToSolveRespondsWithUnprocessableEntityStatusOnInvalidFormatException() throws Exception {

        final InvalidFormatException exception = mock(InvalidFormatException.class);
        Mockito.when(service.solve(puzzleResource)).thenThrow(exception);
        Mockito.when(exception.getMessage()).thenReturn("Bad format");

        expected.expect(ResponseStatusException.class);
        expected.expectMessage("422 UNPROCESSABLE_ENTITY \"Bad format\"");

        controller.solve(puzzleResource);
    }

    @Test
    public void callToSolveRespondsWithBadRequestStatusOnInvalidPuzzleException() throws Exception {

        final InvalidPuzzleException exception = mock(InvalidPuzzleException.class);
        Mockito.when(service.solve(puzzleResource)).thenThrow(exception);
        Mockito.when(exception.getMessage()).thenReturn("Bad puzzle");

        expected.expect(ResponseStatusException.class);
        expected.expectMessage("400 BAD_REQUEST \"Bad puzzle\"");

        controller.solve(puzzleResource);
    }
}
