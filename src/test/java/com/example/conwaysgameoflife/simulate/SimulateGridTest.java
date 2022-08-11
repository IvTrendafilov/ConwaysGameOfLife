package com.example.conwaysgameoflife.simulate;

import com.example.conwaysgameoflife.Grid.Grid;
import com.example.conwaysgameoflife.Simulation.SimulateGame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimulateGridTest {
    @Spy
    private SimulateGame simulateGame;

    private Grid grid;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        grid = new Grid();
    }

    @Test
    public void findNextGeneration_changer1() throws Exception {
        grid.setWidth(3);
        grid.setHeight(3);

        int[] changer1 = {0, 1, 0,
                0, 1, 0,
                0, 1, 0};

        int[] changer1ExpectedOutput =
                {0, 0, 0,
                        1, 1, 1,
                        0, 0, 0};

        grid.setContent(changer1);
        Grid grid = simulateGame.findNextGen(this.grid);


        assertThat("correct result for next gen", Arrays.equals(grid.getContent(), changer1ExpectedOutput), is(true));
    }

    @Test
    public void findNextGeneration_changer2() throws Exception {
        grid.setWidth(4);
        grid.setHeight(4);

        int[] changer2 = {1, 1, 0, 0,
                1, 1, 0, 0,
                0, 0, 1, 1,
                0, 0, 1, 1};

        int[] changer2ExpectedOutput = {1, 1, 0, 0,
                1, 0, 0, 0,
                0, 0, 0, 1,
                0, 0, 1, 1};

        grid.setContent(changer2);
        Grid grid = simulateGame.findNextGen(this.grid);


        assertThat("correct result for next gen", Arrays.equals(grid.getContent(), changer2ExpectedOutput), is(true));

    }
}
