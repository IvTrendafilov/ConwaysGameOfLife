package com.example.conwaysgameoflife.service;

import com.example.conwaysgameoflife.Grid.Grid;
import com.example.conwaysgameoflife.Service.Service;
import com.example.conwaysgameoflife.Simulation.SimulateGame;
import com.example.conwaysgameoflife.ValidateGrid.ValidateGrid;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class ServiceTest {
    @InjectMocks
    private Service service;

    @Mock
    private SimulateGame simulateGame;

    @Mock
    private ValidateGrid validateGrid;

    private Grid grid;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

        PodamFactory podamFactory = new PodamFactoryImpl();
        grid = podamFactory.manufacturePojo(Grid.class);

    }

    @Test
    public void findNextGeneration() throws Exception {
        doNothing().when(validateGrid).ValidateGrid(any(Grid.class));
        when(simulateGame.findNextGen(any(Grid.class))).thenReturn(grid);

        Grid grid = service.findNextGen(this.grid);

        assertThat("different from null", grid, is(not(nullValue())));
    }

    @Test
    public void findSpecificGenerations() throws Exception {
        doNothing().when(validateGrid).ValidateGrid(any(Grid.class));
        when(simulateGame.findNextGen(any(Grid.class))).thenReturn(grid);

        Grid grid = service.findSpecificGen(this.grid, 5);

        assertThat("different from null", grid, is(not(nullValue())));
        verify(simulateGame, times(5)).findNextGen(any(Grid.class));
    }
}
