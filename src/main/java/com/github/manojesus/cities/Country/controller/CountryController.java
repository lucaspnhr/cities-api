package com.github.manojesus.cities.Country.controller;

import com.github.manojesus.cities.Country.entities.Country;
import com.github.manojesus.cities.Country.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/Countries")
public class CountryController {
    CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public Page<Country> selectCountry(Pageable country){
        return countryService.selectAll(country);
    }

    @PostMapping
    public void insertCountry(@RequestBody  Country country){
        countryService.insert(country);
    }

    @PutMapping(path="{countryId}")
    public void updateCountry(@PathVariable("countryId") final Long id,
                                                                   @RequestParam (required = false)final String name,
                                                                   @RequestParam (required = false)final String portugueseName,
                                                                    @RequestParam (required = false)final String uf,
                                                                    @RequestParam (required = false)final Integer bacen ){
        countryService.updateCountry(id,name,portugueseName,uf,bacen);
    }
    @DeleteMapping(path="{countryId}")
    public void deleteCountry(@PathVariable("countryId")final Long countryId){
        countryService.deleteCountry(countryId);
    }
}
