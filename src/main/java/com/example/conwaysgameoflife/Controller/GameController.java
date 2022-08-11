package com.example.conwaysgameoflife.Controller;

import com.example.conwaysgameoflife.Grid.Grid;
import com.example.conwaysgameoflife.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class GameController {

    private final Service service;

    @Autowired
    public GameController(Service service) {
        this.service = service;
    }

    @RequestMapping(value = "/grid", method = RequestMethod.POST)
    public ResponseEntity<Grid> nextGeneration(@RequestBody Grid grid) {
        return ResponseEntity.ok(service.findNextGen(grid));
    }

    @RequestMapping(value = "/grid/{generation}", method = RequestMethod.POST)
    public ResponseEntity<Grid> specificGeneration(@RequestBody Grid grid, @PathVariable("generation") Integer generation) {
        return ResponseEntity.ok(service.findSpecificGen(grid, generation));
    }
}
