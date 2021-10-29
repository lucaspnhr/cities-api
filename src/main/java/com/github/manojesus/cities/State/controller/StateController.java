package com.github.manojesus.cities.State.controller;

import com.github.manojesus.cities.Country.entities.Country;
import com.github.manojesus.cities.State.entities.State;
import com.github.manojesus.cities.State.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/States")
public class StateController {
    StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public Page<State> selectCountry(Pageable state){
        return stateService.selectAll(state);
    }

    @PostMapping
    public void insertCountry(@RequestBody State state){
        stateService.insert(state);
    }

    @PutMapping(path="{stateId}")
    public void updateCountry(@PathVariable("stateId") final Long id,
                                                                   @RequestParam (required = false)final String name,
                                                                   @RequestParam (required = false)final String uf,
                                                                   @RequestParam (required = false)final Integer ibge,
                                                                    @RequestParam (required = false)final Country country,
                                                                    @RequestParam (required = false)final List<Integer> ddd ){
        stateService.updateState(id,name,uf,ibge,country,ddd);
    }
    @DeleteMapping(path="{stateId}")
    public void deleteCountry(@PathVariable("stateId")final Long stateId){
        stateService.deleteState(stateId);
    }
}
