package com.endtoend.historyOfMine.controllers;

import com.endtoend.historyOfMine.forms.RelativeForm;
import com.endtoend.historyOfMine.service.RelativeService;
import com.endtoend.historyOfMine.tables.Relative;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relative")
public class RelativeController {
    private final RelativeService relativeService;

    public RelativeController(RelativeService relativeService) {
        this.relativeService = relativeService;
    }

    @PostMapping
    public void addRelative(@RequestBody RelativeForm relativeForm){
        relativeService.addRelative(relativeForm);
    }

    @GetMapping
    public List<Relative> getAllRelatives(){
        return relativeService.getAllUserRelatives();
    }
}
