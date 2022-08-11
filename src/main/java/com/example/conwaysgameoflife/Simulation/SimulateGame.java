package com.example.conwaysgameoflife.Simulation;

import com.example.conwaysgameoflife.Grid.Grid;
import org.springframework.stereotype.Component;

@Component
public class SimulateGame {

    // Here we make the simulation of the game(and implement the logic of the game)

    // In this method we make a copy of the current grid in the variable nextGen
    // we then add the next generation to nextGen by calling the method createNextGen
    public Grid findNextGen(Grid grid) {
        Grid nextGen = grid.makeCopy();
        nextGen.setContent(createNextGen(nextGen.getContent(), nextGen.getWidth(), nextGen.getHeight()));
        return nextGen;
    }

    // In this method we create/find the next generation
    private int[] createNextGen(int[] content, int width, int height) {
        int[] newContent = new int[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int cell = content[i * width + j];
                // here we check the number of neighbors
                int aliveNeighborsCount = countAliveNeighbors(content, height, width, i, j);
                // here to add a 0 or 1 we check the return value of isCellAlive, if we have true we add 1, else we add 0
                newContent[i * width + j] = isCellAlive(cell, aliveNeighborsCount) ? 1 : 0;
            }
        }
        return newContent;
    }

    // In this method we check if a cell is alive - by implementing the game logic
    // if a cell is dead and has 3 neighbours it returns true, if a cell is alive and has 2 or 3 neighbours it returns true
    // this makes sense when you read the above comment
    private boolean isCellAlive(int self, int neighborCount) {
        if (self == 0) {
            return neighborCount == 3;
        } else {
            return neighborCount == 2 || neighborCount == 3;
        }
    }

    // In this method we count the neighbors,
    // One can imagine the starting positions as such - [i+1,j-1][i+1,j][i+1,j+1]
    //                                                   [i,j-1] [i,j] [i,j+1]
    //                                                  [i-1,j-1][i-1,j][i-1,j+1]
    private static int countAliveNeighbors(int[] content, int height, int width, int i, int j) {
        int startPosX = (i - 1 < 0) ? i : i - 1;
        int startPosY = (j - 1 < 0) ? j : j - 1;
        int endPosX = (i + 1 >= width) ? i : i + 1;
        int endPosY = (j + 1 >= height) ? j : j + 1;

        int count = 0;

        for (int row = startPosX; row <= endPosX; row++) {
            for (int col = startPosY; col <= endPosY; col++) {
                if (!(row == i && col == j)) { //exclude self
                    count += content[row * height + col];
                }
            }
        }
        return count;
    }

}