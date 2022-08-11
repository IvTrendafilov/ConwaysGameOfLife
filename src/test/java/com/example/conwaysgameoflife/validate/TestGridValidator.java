package com.example.conwaysgameoflife.validate;

import com.example.conwaysgameoflife.Grid.Grid;
import com.example.conwaysgameoflife.ValidateGrid.ValidateGrid;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.xml.bind.ValidationException;

public class TestGridValidator {
    @Spy
    private ValidateGrid validateGrid;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGridValid_invalid() throws Exception {
        Grid grid = new Grid();
        grid.setWidth(5);
        grid.setHeight(5);
        grid.setContent(new int[100]);

        validateGrid.ValidateGrid(grid);
    }

    @Test
    public void isGridValid_valid() throws Exception {
        Grid grid = new Grid();
        grid.setWidth(5);
        grid.setHeight(5);
        grid.setContent(new int[25]);

        validateGrid.ValidateGrid(grid);
    }


}
