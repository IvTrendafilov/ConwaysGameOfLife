package com.example.conwaysgameoflife.ValidateGrid;

import com.example.conwaysgameoflife.Grid.Grid;
import org.springframework.stereotype.Component;


import java.util.Arrays;

@Component
public class ValidateGrid {

    // We need to make sure that the grid is valid and there are no faulty items or dimension in it
    public void ValidateGrid(Grid grid) {
        if (grid.getContent() != null) {

            checkForWrongDimensions(grid);
            checkForInvalidCells(grid);

        }
    }

    // In this method we check if each cell is 0 or 1 if there is a cell with a different value we throw error
    private void checkForInvalidCells(Grid grid) {
        long wrongCells = Arrays.stream(grid.getContent()).filter(i -> i < 0 && i > 1).count();

        if (wrongCells > 0) {
            throw new IllegalArgumentException("There are cells different from 0 or 1");
        }
    }

    // In this method we check for the dimension of the grid
    private void checkForWrongDimensions(Grid grid) {
        if (grid.getHeight() * grid.getWidth() != grid.getContent().length) {
            throw new IllegalArgumentException("Wrong dimensions");
        }
    }


}
