package com.example.conwaysgameoflife.Service;

import com.example.conwaysgameoflife.Grid.Grid;

import com.example.conwaysgameoflife.Simulation.SimulateGame;
import com.example.conwaysgameoflife.ValidateGrid.ValidateGrid;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final ValidateGrid validateGrid;

    private final SimulateGame gameSimulator;

    @Autowired
    public Service(ValidateGrid validateGrid, SimulateGame gameSimulator) {
        this.validateGrid = validateGrid;
        this.gameSimulator = gameSimulator;
    }

    public Grid findNextGen(Grid grid) {
        validateGrid.ValidateGrid(grid);

        return gameSimulator.findNextGen(grid);
    }

    public Grid findSpecificGen(Grid grid, Integer generation) {
        validateGrid.ValidateGrid(grid);

        for (int i = 0; i < generation; i++) {
            grid = findNextGen(grid);
        }
        return grid;
    }


    // These two methods can be used when the grid doesnt change anymore to cancel the application , but decidet not to add them as one
    // can always pause the game and make new cells alive
    public List<Grid> calculateAllGenerations(Grid grid) {
        validateGrid.ValidateGrid(grid);

        ArrayList<Grid> gridCollection = new ArrayList<>();

        while (!stoppedChanging(gridCollection)) {
            grid = findNextGen(grid);
            gridCollection.add(grid);
        }

        return gridCollection;
    }

    private boolean stoppedChanging(List<Grid> gridList) {
        if (gridList.size() >= 2) {
            int[] lastGridContent = gridList.get(gridList.size() - 1).getContent();
            int[] previousGridContent = gridList.get(gridList.size() - 2).getContent();
            return Arrays.equals(lastGridContent, previousGridContent);
        }
        return false;
    }
}
