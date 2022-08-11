package com.example.conwaysgameoflife.controller;

import com.example.conwaysgameoflife.ConwaysGameOfLifeApplication;
import com.example.conwaysgameoflife.Grid.Grid;
import com.example.conwaysgameoflife.Service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConwaysGameOfLifeApplication.class)
public class TestGameController {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Service service;

    @Autowired
    protected ObjectMapper objectMapper;

    private MediaType applicationJsonMediaType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());


    private Grid grid;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.grid = new Grid();
        this.grid.setWidth(10);
        this.grid.setHeight(10);
        this.grid.setContent(new int[100]);
    }


    @Test
    public void nextGeneration() throws Exception {
        when(service.findNextGen(any(Grid.class))).thenReturn(grid);
        mockMvc.perform(post("/grid").contentType(applicationJsonMediaType)
                        .content(asJsonString(grid)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void specificGeneration() throws Exception {
        when(service.findSpecificGen(any(Grid.class), anyInt())).thenReturn(grid);
        mockMvc.perform(post("/grid", 10).contentType(applicationJsonMediaType)
                        .content(asJsonString(grid)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
